package com.test.serializable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SubReqClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public SubReqClientHandler(){

    }
    public void channelActive(ChannelHandlerContext context){
        for(int i=0;i<10;i++){
            context.write(subReq(i));
        }
        context.flush();
    }
    private SubscribeReq subReq(int i){
        SubscribeReq req=new SubscribeReq();
        req.setAddress("南京市江宁区方山");
        req.setPhoneNumber("15652221505");
        req.setProductName("Netty guide");
        req.setSubReqID(i);
        req.setUserName("li");
        return  req;
    }
    public void channelRead(ChannelHandlerContext context,Object msg){
        System.out.println("client Accept:"+msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("client Accept:"+byteBuf.toString());

    }

    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }
}
