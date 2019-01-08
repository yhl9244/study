package netty.message.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.message.protocol.request.LoginRequestPacket;
import netty.message.protocol.response.LoginResponsePacket;
import netty.message.session.Session;
import netty.message.util.LoginUtil;
import netty.message.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /*@Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userName = loginResponsePacket.getUserName();
        String userId = loginResponsePacket.getUserId();
        if (loginResponsePacket.isSuccess()) {
            //System.out.println(new Date() + ": 客户端登录成功");
            //LoginUtil.markAsLogin(ctx.channel());
            System.out.println("[" + userName + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            //System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            System.out.println("[" + userName + "]客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
