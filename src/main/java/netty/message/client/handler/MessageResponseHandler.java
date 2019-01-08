package netty.message.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.message.protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        //System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        String fromUserid = messageResponsePacket.getFromUserid();
        String fromUsername = messageResponsePacket.getFromUsername();
        System.out.println(fromUserid + ":" + fromUsername + " -> " + messageResponsePacket.getMessage());
    }
}
