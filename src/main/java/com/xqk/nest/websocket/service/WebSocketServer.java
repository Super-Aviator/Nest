package com.xqk.nest.websocket.service;

import com.xqk.nest.websocket.handlers.MessageChannelHandler;
import com.xqk.nest.websocket.handlers.SignChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import java.net.InetSocketAddress;

public class WebSocketServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(8081))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new HttpServerCodec(),//消息编解码器
                                    new HttpObjectAggregator(655360),//最大消息长度，并聚合消息
                                    new WebSocketServerProtocolHandler("/chat"),//websocket路径
                                    new SignChannelHandler(),
                                    new MessageChannelHandler()
                            );
                        }
                    });
            ChannelFuture future = b.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully().sync();
            work.shutdownGracefully().sync();
        }
    }
}