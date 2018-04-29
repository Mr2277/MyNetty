package com.test.six.one.lifecycle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import java.net.InetSocketAddress;
public class EchoServer {
    private final int port=6789;
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap=new ServerBootstrap();
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                  socketChannel.pipeline().addLast(new EchoServerHandler());
            }
        });
        ChannelFuture channelFuture = bootstrap.bind();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("success");
                }
                else{
                    System.out.println("failed");
                }
            }
        });
        }
        public static void main(String[] args) throws InterruptedException {
          new EchoServer().start();
        }
}
