package netty.login.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.protocol.Packet;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.PacketCodec;
import netty.protocol.response.LoginResponsePacket;
import netty.protocol.response.MessageResponsePacket;
import netty.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by suneee on 2018/11/5.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("yhl");
        loginRequestPacket.setUserName("yhl");

        // 编码
        ByteBuf encode = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(encode);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf response = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodec.INSTANCE.decode(response);
        if(packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + ":客户端登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else {
                System.err.println(new Date() + ":客户端登录失败,原因:" + loginResponsePacket.getReason());
            }
        }else if(packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ":收到服务器消息:" + messageResponsePacket.getMessage());
        }
    }
}
