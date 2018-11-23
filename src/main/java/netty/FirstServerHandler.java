package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by suneee on 2018/10/24.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 服务端读数据 -> " + ((ByteBuf)msg).toString(Charset.forName("utf-8")));

        // 向客户端回复数据
        /*System.out.println(new Date() + "服务端回复数据");
        ByteBuf buffer = getByteBuffer(ctx, "谢谢回复!");
        ctx.channel().writeAndFlush(buffer);*/
    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx, String msg) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "欢迎您连接成功: " + ctx.channel().toString();
        ByteBuf buffer = getByteBuffer(ctx, msg);
        ctx.channel().writeAndFlush(buffer);
    }
}
