package com.test.six.one.lifecycle;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient() {
        this.host = "127.0.0.1";
        this.port = 6789;
    }
    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port)).handler(new ChannelInitializer<SocketChannel>() {

            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new EchoClientHandler());
            }
        });
        ChannelFuture  channelFuture= bootstrap.connect();

        channelFuture.addListener(new ChannelFutureListener() {
           @Override
           public void operationComplete(ChannelFuture channelFuture) throws Exception {
               if(channelFuture.isSuccess()){
                   System.out.println("s");
               }
               else{
                   System.out.println("f");
               }
           }
       });
    }
    public static void main(String[] args) throws InterruptedException {
        new EchoClient().start();
    }
}
