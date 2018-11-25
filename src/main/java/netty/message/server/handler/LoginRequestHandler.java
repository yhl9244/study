package netty.message.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.message.protocol.request.LoginRequestPacket;
import netty.message.protocol.response.LoginResponsePacket;
import netty.message.util.LoginUtil;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        if(isValid(msg)){
            // 校验成功
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ":登录成功");
        }else{
            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.err.println(new Date() + ":登录失败");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean isValid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
