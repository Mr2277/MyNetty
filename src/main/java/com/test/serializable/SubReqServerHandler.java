package com.test.serializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
    public void channelRead(ChannelHandlerContext context,Object msg){
        SubscribeReq req=(SubscribeReq)msg;
        if("Lilinfeng".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req:["+req.toString()+"]");
            context.writeAndFlush(resp(req.getSubReqID()));
        }
    }
    private SubscibeResp resp(int subReqID){
        SubscibeResp resp=new SubscibeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed,3 days later,sent");
        return resp;
    }
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }
}
