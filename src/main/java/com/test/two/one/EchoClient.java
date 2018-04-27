package com.test.two.one;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
@ChannelHandler.Sharable

public class EchoClient {
    private final String host;
    private final int port;
    public EchoClient(String host,int port){
        this.host=host;
        this.port=port;
    }
    public void start() throws InterruptedException {
        System.out.println("1");

        EventLoopGroup group=new NioEventLoopGroup();
        System.out.println("2");

        try{

            Bootstrap b=new Bootstrap();
            System.out.println("3");

            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port)).handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    System.out.println("4");

                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            System.out.println("5");

            ChannelFuture f=b.connect().sync();
            System.out.println("6");

            f.channel().closeFuture().sync();
        }finally {
               group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        String host="127.0.0.1";
        int port=Integer.parseInt("9999");
        new EchoClient(host,port).start();
        }
}
