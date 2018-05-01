package com.test.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class SubReqServer {
    public void bind(int port) throws InterruptedException {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap=new ServerBootstrap();
        final MarshallerFactory marshallerFactory=Marshalling.getMarshallerFactory("serial");
        final MarshallingConfiguration configuration=new MarshallingConfiguration();
        configuration.setVersion(4);
        final UnmarshallerProvider provider=new DefaultUnmarshallerProvider(marshallerFactory,configuration);
        final MarshallerProvider marshallerProvider=new DefaultMarshallerProvider(marshallerFactory,configuration);
        serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new MarshallingDecoder(provider,1024));
                socketChannel.pipeline().addLast(new MarshallingEncoder(marshallerProvider));
                socketChannel.pipeline().addLast(new SubReqServerHandler());
            }
        });
        ChannelFuture channelFuture=serverBootstrap.bind(port).sync();
        channelFuture.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
        }
        public static void main(String[] args) throws InterruptedException {
         int port=8080;
         new SubReqServer().bind(port);
        }
}
