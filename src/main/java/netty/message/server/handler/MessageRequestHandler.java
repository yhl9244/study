package netty.message.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.message.protocol.request.MessageRequestPacket;
import netty.message.protocol.response.MessageResponsePacket;
import netty.message.session.Session;
import netty.message.util.SessionUtil;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        // 1.获取发送方Session信息
        Session session = SessionUtil.getSession(ctx.channel());
        // 2.通过消息发送方的Session信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserid(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        //System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        //messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        // 3.拿到消息接收方channel
        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserid());

        // 4.将消息发送给消息接收方
        if(channel != null && SessionUtil.hasLogin(channel)){
            channel.writeAndFlush(messageResponsePacket);
        }else{
            System.err.println("[" + messageRequestPacket.getToUserid() + "] 不在线，发送失败!");
        }

        //ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
