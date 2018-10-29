package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端写数据:" + new Date());

        // 获取数据
        ByteBuf byteBuf = getByteBuf(ctx, "我正在上线");

        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
    }*/

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String msg) {
        // 获取二进制抽象
        ByteBuf buffer = ctx.alloc().buffer(1024);
        // 准备数据
        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));
        // 填充数据到buffer
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = ((ByteBuf)msg).toString(Charset.forName("utf-8"));
        System.out.println("客户端收到数据:" + message);
        if(!message.contains("谢谢")) {
            ByteBuf byteBuf = getByteBuf(ctx, "我已上线,谢谢!");
            ctx.channel().writeAndFlush(byteBuf);
        }
    }
}
