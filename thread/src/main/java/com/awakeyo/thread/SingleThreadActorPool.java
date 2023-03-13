package com.awakeyo.thread;

import com.awakeyo.util.StringUtils;
import com.awakeyo.util.ThreadUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version : 1.0
 * @ClassName: SingleThreadActorPool
 * @Description: 基于单独线程执行实现的Actor模型
 * @Auther: awake
 * @Date: 2023/3/10 11:24
 **/
public class SingleThreadActorPool implements IThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(SingleThreadActorPool.class);

    private ExecutorService[] executors;

    public int executorsSize;


    public SingleThreadActorPool(int executorsSize) {
        this.executorsSize = executorsSize;
        executors = new ExecutorService[executorsSize];
        for (int i = 0; i < executorsSize; i++) {
            TaskThreadFactory taskThreadFactory = new TaskThreadFactory(i);
            ExecutorService executor = Executors.newSingleThreadExecutor(taskThreadFactory);
            executors[i] = executor;
        }
    }

    @Override
    public void execute(int executorHash, Runnable runnable) {
        executors[Math.abs(executorHash % executorsSize)].execute(SafeRunnable.valueOf(runnable));
    }

    public static class TaskThreadFactory implements ThreadFactory {
        private final int poolNumber;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;

        public TaskThreadFactory(int poolNumber) {
            this.group = ThreadUtils.currentThreadGroup();
            this.poolNumber = poolNumber;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            String threadName = StringUtils.format("SingleTaskActor-p{}-t{}", poolNumber + 1, threadNumber.getAndIncrement());
            Thread thread = new FastThreadLocalThread(group, runnable, threadName);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setUncaughtExceptionHandler((t, e) -> logger.error(t.toString(), e));
            return thread;
        }
    }
}
