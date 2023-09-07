package com.awake.net.router;

import com.awake.event.manger.EventBus;
import com.awake.net.event.ServerExceptionEvent;
import com.awake.net.packet.EncodedPacketInfo;
import com.awake.net.packet.IPacket;
import com.awake.net.packet.common.Error;
import com.awake.net.packet.common.Heartbeat;
import com.awake.net.router.answer.AsyncAnswer;
import com.awake.net.router.answer.SyncAnswer;
import com.awake.net.router.attachment.IAttachment;
import com.awake.net.router.attachment.SignalAttachment;
import com.awake.net.router.exception.ErrorResponseException;
import com.awake.net.router.exception.NetTimeOutException;
import com.awake.net.router.exception.UnexpectedProtocolException;
import com.awake.net.session.Session;
import com.awake.thread.pool.model.ThreadActorPoolModel;
import com.awake.util.ExceptionUtils;
import com.awake.util.JsonUtils;
import com.awake.util.StringUtils;
import io.netty.util.concurrent.FastThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @version : 1.0
 * @ClassName: Router
 * @Description: 路由  负责消息的处理以及实现同步-异步-Rpc
 * @Auther: awake
 * @Date: 2023/8/8 19:47
 **/

public class Router implements IRouter {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    public static final long DEFAULT_TIMEOUT = 3000;

    /**
     * 使用不同的线程池，让线程池之间实现隔离，互不影响
     */
    private static ThreadActorPoolModel executors;

    /**
     * 路由的转发都会到异步线程池执行
     * 作为服务器接收方，会把receive收到的attachment存储在这个地方，只针对task线程。线程变量
     * atReceiver会设置attachment，但是在方法调用完成会取消，不需要过多关注。
     * asyncAsk会再次设置attachment，需要重点关注。
     */
    private final FastThreadLocal<IAttachment> serverReceiverAttachmentThreadLocal = new FastThreadLocal<>();

    /**
     * 作为客户端，会把发送出去的signalAttachment存储在这个地方
     * key：signalId
     */
    private static final Map<Integer, SignalAttachment> signalAttachmentMap = new ConcurrentHashMap<>(1000);

    @Override
    public void receive(Session session, IPacket packet, IAttachment attachment) {
        if (packet.protocolId() == Heartbeat.PROTOCOL_ID) {
            logger.info("heartbeat");
            return;
        }
        // 发送者（客户端）同步和异步消息的接收，发送者通过signalId判断重复
        if (attachment != null) {
            switch (attachment.packetType()) {
                case SIGNAL_PACKET:
                    var signalAttachment = (SignalAttachment) attachment;

                    if (signalAttachment.isClient()) {
                        // 服务器收到signalAttachment，不做任何处理
                        signalAttachment.setClient(false);
                    } else {
                        // 客户端收到服务器应答，客户端发送的时候isClient为true，服务器收到的时候将其设置为false
                        var removedAttachment = signalAttachmentMap.remove(signalAttachment.getSignalId());
                        if (removedAttachment != null) {
                            // 这里会让之前的CompletableFuture得到结果，从而像asyncAsk之类的回调到结果
                            removedAttachment.getResponseFuture().complete(packet);
                        } else {
                            logger.error("client receives packet:[{}] and attachment:[{}] from server, but clientAttachmentMap has no attachment, perhaps timeout exception.", JsonUtils.object2String(packet), JsonUtils.object2String(attachment));
                        }
                        // 注意：这个return，这样子，asyncAsk的结果就返回了。
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
        // 分发逻辑
        dispatch(session, packet, attachment);
    }

    @Override
    public void send(Session session, IPacket packet) {
        // 服务器异步返回的消息的发送会有signalAttachment，验证返回的消息是否满足
        var serverSignalAttachment = serverReceiverAttachmentThreadLocal.get();
        send(session, packet, serverSignalAttachment);
    }

    @Override
    public void send(Session session, IPacket packet, IAttachment attachment) {
        if (session == null) {
            logger.error("session is null and can not be sent.");
            return;
        }
        if (packet == null) {
            logger.error("packet is null and can not be sent.");
            return;
        }

        var packetInfo = EncodedPacketInfo.valueOf(packet, attachment);

        var channel = session.getChannel();
        if (!channel.isActive() || !channel.isWritable()) {
            logger.warn("send msg error, protocolId=[{}] isActive=[{}] isWritable=[{}]", packet.protocolId(), channel.isActive(), channel.isWritable());
        }
        channel.writeAndFlush(packetInfo);
    }

    @Override
    public <T extends IPacket> SyncAnswer<T> syncAsk(Session session, IPacket packet, Class<T> answerClass, Object argument) throws Exception {
        var clientSignalAttachment = new SignalAttachment();
        int taskExecutorHash = executors.calTaskExecutorHash(argument);
        clientSignalAttachment.setTaskExecutorHash(taskExecutorHash);

        try {
            signalAttachmentMap.put(clientSignalAttachment.getSignalId(), clientSignalAttachment);
            // 里面调用的依然是：send方法发送消息
            send(session, packet, clientSignalAttachment);

            var responsePacket = clientSignalAttachment.getResponseFuture().get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);

            if (responsePacket.protocolId() == Error.errorProtocolId()) {
                throw new ErrorResponseException((Error) responsePacket);
            }
            if (answerClass != null && answerClass != responsePacket.getClass()) {
                throw new UnexpectedProtocolException("client expect protocol:[{}], but found protocol:[{}]", answerClass, responsePacket.getClass().getName());
            }

            return new SyncAnswer<>((T) responsePacket, clientSignalAttachment);
        } catch (TimeoutException e) {
            throw new NetTimeOutException("syncAsk timeout exception, ask:[{}], attachment:[{}]", JsonUtils.object2String(packet), JsonUtils.object2String(clientSignalAttachment));
        } finally {
            signalAttachmentMap.remove(clientSignalAttachment.getSignalId());
        }
    }

    /**
     * 注意：
     * 1.这个里面其实还是调用send发送的消息
     * 2.这个argument的参数，只用于provider处哪个线程执行，其实就是hashId，如：工会业务，则传入guildId，回调回来后，一定会在发起者线程。
     */
    @Override
    public <T extends IPacket> AsyncAnswer<T> asyncAsk(Session session, IPacket packet, Class<T> answerClass, Object argument) {
        var clientSignalAttachment = new SignalAttachment();
        var taskExecutorHash = executors.calTaskExecutorHash(argument);

        clientSignalAttachment.setTaskExecutorHash(taskExecutorHash);

        // 服务器在同步或异步的消息处理中，又调用了同步或异步的方法，这时候threadReceiverAttachment不为空
        var serverSignalAttachment = serverReceiverAttachmentThreadLocal.get();

        try {
            var asyncAnswer = new AsyncAnswer<T>();
            asyncAnswer.setSignalAttachment(clientSignalAttachment);

            clientSignalAttachment.getResponseFuture()
                    // 因此超时的情况，返回的是null
                    .completeOnTimeout(null, DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS).thenApply(answer -> {
                if (answer == null) {
                    throw new NetTimeOutException("async ask [{}] timeout exception", packet.getClass().getSimpleName());
                }

                if (answer.protocolId() == Error.errorProtocolId()) {
                    throw new ErrorResponseException((Error) answer);
                }

                if (answerClass != null && answerClass != answer.getClass()) {
                    throw new UnexpectedProtocolException("client expect protocol:[{}], but found protocol:[{}]", answerClass, answer.getClass().getName());
                }
                return answer;
            }).whenComplete((answer, throwable) -> {
                // 注意：进入这个方法的时机是：在上面的receive方法中，由于是asyncAsk的消息，attachment不为空，会调用CompletableFuture的complete方法
                try {
                    signalAttachmentMap.remove(clientSignalAttachment.getSignalId());
                    // 接收者在同步或异步的消息处理中，又调用了异步的方法，这时候threadServerAttachment不为空
                    if (serverSignalAttachment != null) {
                        serverReceiverAttachmentThreadLocal.set(serverSignalAttachment);
                    }
                    // 如果有异常的话，whenCompleteAsync的下一个thenAccept不会执行
                    if (throwable != null) {
                        var notCompleteCallback = asyncAnswer.getNotCompleteCallback();
                        if (notCompleteCallback != null) {
                            notCompleteCallback.run();
                        } else {
                            logger.error(ExceptionUtils.getMessage(throwable));
                        }
                        return;
                    }
                    // 异步返回，回调业务逻辑
                    asyncAnswer.setFuturePacket((T) answer);
                    asyncAnswer.consume();
                } catch (Throwable throwable1) {
                    logger.error("Asynchronous callback method [ask:{}][answer:{}] error", packet.getClass().getSimpleName(), answer.getClass().getSimpleName(), throwable1);
                } finally {
                    if (serverSignalAttachment != null) {
                        serverReceiverAttachmentThreadLocal.set(null);
                    }
                }

            });
            signalAttachmentMap.put(clientSignalAttachment.getSignalId(), clientSignalAttachment);
            // 等到上层调用whenComplete才会发送消息
            asyncAnswer.setAskCallback(() -> send(session, packet, clientSignalAttachment));
            return asyncAnswer;
        } catch (Exception e) {
            signalAttachmentMap.remove(clientSignalAttachment.getSignalId());
            throw e;
        }
    }

    @Override
    public void atReceiver(Session session, IPacket packet, IAttachment attachment) {
        try {
            // 接收者（服务器）同步和异步消息的接收
            if (attachment != null) {
                serverReceiverAttachmentThreadLocal.set(attachment);
            }
            // 调用PacketReceiver,进行真正的业务处理,这个submit只是根据packet找到protocolId，然后调用对应的消息处理方法
            // 这个在哪个线程处理取决于：这个上层的PacketReceiverTask被丢到了哪个线程中
            PacketBus.route(session, packet, attachment);
        } catch (Exception e) {
            EventBus.publicEvent(ServerExceptionEvent.valueOf(session, packet, attachment, e));
            logger.error(StringUtils.format("e[uid:{}][sid:{}] unknown exception", session.getUid(), session.getSid(), e.getMessage()), e);
        } catch (Throwable t) {
            logger.error(StringUtils.format("e[uid:{}][sid:{}] unknown error", session.getUid(), session.getSid(), t.getMessage()), t);
        } finally {
            // 如果有服务器在处理同步或者异步消息的时候由于错误没有返回给客户端消息，则可能会残留serverAttachment，所以先移除
            if (attachment != null) {
                serverReceiverAttachmentThreadLocal.set(null);
            }
        }
    }

    /**
     * 分發到不同線程處理 保證actor模型
     *
     * @param session
     * @param packet
     * @param attachment
     */
    public void dispatch(Session session, IPacket packet, @Nullable IAttachment attachment) {
        if (attachment == null) {
            dispatchBySession(session, packet, attachment);
        } else {
            dispatchByAttachment(session, packet, attachment);
        }
    }

    private void dispatchBySession(Session session, IPacket packet, @Nullable IAttachment attachment) {
        long uid = session.getUid();
        if (uid > 0) {
            executors.execute((int) uid, () -> atReceiver(session, packet, attachment));
        } else {
            executors.execute((int) session.getSid(), () -> atReceiver(session, packet, attachment));
        }
    }

    private void dispatchByAttachment(Session session, IPacket packet, IAttachment attachment) {
        switch (attachment.packetType()) {
            case SIGNAL_PACKET:
                executors.execute(((SignalAttachment) attachment).taskExecutorHash(), () -> atReceiver(session, packet, attachment));
                break;
            case NO_ANSWER_PACKET:
            default:
        }
    }


}
