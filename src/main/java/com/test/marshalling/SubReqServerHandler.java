package com.test.marshalling;

import com.test.serializable.SubscibeResp;
import com.test.serializable.SubscribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext context, Object msg){
        System.out.println("Srever channelRead");
        SubscribeReq req=(SubscribeReq)msg;
        if("li".equalsIgnoreCase(req.getUserName())){
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
