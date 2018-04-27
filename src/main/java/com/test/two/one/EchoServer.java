package com.test.two.one;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;
    public EchoServer(int port){
        this.port=port;
    }
    public static void main(String[] args) throws InterruptedException {
        int port=Integer.parseInt("9999");
        new EchoServer(port).start();
    }
    public void start() throws InterruptedException {
        System.out.println("1");
        final EchoServerHandler serverHandler=new EchoServerHandler();
        System.out.println("2");

        EventLoopGroup group=new NioEventLoopGroup();
        System.out.println("3");

        try{
            ServerBootstrap b=new ServerBootstrap();
            System.out.println("4");
            b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                     socketChannel.pipeline().addLast(serverHandler);
                     }
            });
            System.out.println("5");
            ChannelFuture future=b.bind().sync();
            System.out.println("6");

            future.channel().closeFuture().sync();
            System.out.println("7");

        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
