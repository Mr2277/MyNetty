package com.test.serializable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;

public class SubReqClient {
    public void connect(int port,String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port)).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                  socketChannel.pipeline().addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                  socketChannel.pipeline().addLast(new ObjectEncoder());
                  socketChannel.pipeline().addLast(new SubReqClientHandler());
            }
        });
        ChannelFuture channelFuture=bootstrap.connect(host,port).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                   if(channelFuture.isSuccess()){
                       System.out.println("client success");
                   }
                   else {
                       System.out.println("client f");
                   }
            }
        });
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
    public static void main(String[] args) throws InterruptedException {
        new SubReqClient().connect(8080,"127.0.0.1");
    }

}
