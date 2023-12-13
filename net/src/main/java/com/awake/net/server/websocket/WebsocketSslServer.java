/*
 * Copyright (C) 2020 The zfoo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.awake.net.server.websocket;

import com.awake.net.handler.ServerRouteHandler;
import com.awake.net.handler.codec.websocket.WebSocketCodecHandler;
import com.awake.net.handler.idle.ServerIdleHandler;
import com.awake.net.server.AbstractServer;
import com.awake.util.IOUtils;
import com.awake.util.net.HostAndPort;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import javax.net.ssl.SSLException;
import java.io.InputStream;

/**
 * @version : 1.0
 * @ClassName: WebsocketSslClient
 * @Description: websocket通信
 * @Auther: awake
 * @Date: 2023/8/2 20:12
 **/
public class WebsocketSslServer extends AbstractServer<SocketChannel> {

    private SslContext sslContext;

    public WebsocketSslServer(HostAndPort host, InputStream pem, InputStream key) {
        super(host);
        try {
            this.sslContext = SslContextBuilder.forServer(pem, key).build();
        } catch (SSLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new IdleStateHandler(0, 0, 180));
        channel.pipeline().addLast(new ServerIdleHandler());
        channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
        channel.pipeline().addLast(new HttpServerCodec(8 * IOUtils.BYTES_PER_KB, 16 * IOUtils.BYTES_PER_KB, 16 * IOUtils.BYTES_PER_KB));
        channel.pipeline().addLast(new HttpObjectAggregator(16 * IOUtils.BYTES_PER_MB));
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/"));
        channel.pipeline().addLast(new ChunkedWriteHandler());
        channel.pipeline().addLast(new WebSocketCodecHandler());
        channel.pipeline().addLast(new ServerRouteHandler());
    }

}
