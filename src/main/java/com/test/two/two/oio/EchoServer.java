package com.test.two.two.oio;

import com.test.two.two.nio.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.sctp.oio.OioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private static final int port = 8080;
    public void start() throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();// 引导辅助程序
        EventLoopGroup group = new OioEventLoopGroup();// 通过oio方式来接收连接和处理连接
        try {
            b.group(group);
            b.channel(OioServerSocketChannel.class);// 设置nio类型的channel
            b.localAddress(new InetSocketAddress(port));// 设置监听端口
            b.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理channel中的Handler，在channel队列中加入一个handler来处理业务
                    ch.pipeline().addLast("myHandler", new EchoServerHandler());
                }
            });
            System.out.println("444444444");
            ChannelFuture f = b.bind().sync();// 配置完毕，開始绑定server，通过调用sync同步方法堵塞直到绑定成功
            f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
            System.out.println("dddddd");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉全部资源包含创建的线程
        }
    }
    public static void main(String[] args) {
        try {
           new EchoServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
