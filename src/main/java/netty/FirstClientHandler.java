package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by suneee on 2018/10/24.
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println(new Date() + "客户端写数据");

        for (int i = 0; i < 1000; i++) {
            // 获取数据
            ByteBuf byteBuffer = getByteBuffer(ctx, "hello world!");

            // 写数据
            ctx.channel().writeAndFlush(byteBuffer);
        }
    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx, String msg) {
        // 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();


        // 准备数据,字符集UTF-8
        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));

        // 填充数据到buffer;
        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String m = ((ByteBuf)msg).toString(Charset.forName("utf-8"));
        System.out.println(new Date() + ": 客户端读数据 -> " + m);
        if(!m.contains("谢谢")){
            // 向服务端回复数据
            System.out.println(new Date() + ": 客户端回复数据");
            String message = "我已经收到,谢谢!";
            ByteBuf buffer = getByteBuffer(ctx,message);
            ctx.channel().writeAndFlush(buffer);
        }
    }
}
