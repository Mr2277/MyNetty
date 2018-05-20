package com.test.encode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


import java.net.InetSocketAddress;

public class Server {
    public void bind(int port) throws InterruptedException {
        final ByteBuf delimiter= Unpooled.copiedBuffer("$_".getBytes());
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                  socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                  socketChannel.pipeline().addLast(new StringDecoder());
                  socketChannel.pipeline().addLast();
            }
        });
        ChannelFuture channelFuture=serverBootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }
    public static void main(String[] args) throws InterruptedException {


        new Server().bind(6666);
    }
}
