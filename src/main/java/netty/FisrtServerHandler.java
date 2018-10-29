package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.Date;

public class FisrtServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器收到数据:" + byteBuf.toString(Charset.forName("utf-8")));

        // 客户端回复数据
        String message = "收到回复,谢谢!";
        ByteBuf buf = getByteBuf(ctx, message);

        ctx.channel().writeAndFlush(buf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String msg) {
        ByteBuf buffer = ctx.alloc().buffer(1024);

        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));

        return buffer.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = new Date() + ":欢迎上线!";
        ByteBuf byteBuf = getByteBuf(ctx, msg);
        ctx.channel().writeAndFlush(byteBuf);
    }
}
