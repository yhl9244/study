package netty.login.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * Created by suneee on 2018/11/20.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponse = new LoginResponsePacket();
        loginResponse.setVersion(msg.getVersion());
        // 登录校验
        if(isValid(msg)){
            // 校验成功
            loginResponse.setSuccess(true);
            System.out.println(new Date() + ":登录成功");
        }else{
            // 校验失败
            loginResponse.setSuccess(false);
            loginResponse.setReason("账号密码校验失败");
            System.err.println(new Date() + ":登录失败");
        }
        ctx.channel().writeAndFlush(loginResponse);
    }

    private boolean isValid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
