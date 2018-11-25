package netty.login.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.message.protocol.Packet;
import netty.message.protocol.PacketCodeC;
import netty.message.protocol.request.LoginRequestPacket;
import netty.message.protocol.request.MessageRequestPacket;
import netty.message.protocol.response.LoginResponsePacket;
import netty.message.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * Created by suneee on 2018/11/5.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf request = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(request);

        // 判断是否是登录请求
        if(packet instanceof LoginRequestPacket){
            // 处理登录消息
            System.out.println(new Date() + ": 客户端开始登录……");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if(isValid(loginRequestPacket)){
                // 校验成功
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ":登录成功");
            }else{
                // 校验失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
                System.err.println(new Date() + ":登录失败");
            }
            // 编码
            ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(encode);
        }else if(packet instanceof MessageRequestPacket){
            // 处理客户端消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ":收到客户端消息:" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private boolean isValid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
