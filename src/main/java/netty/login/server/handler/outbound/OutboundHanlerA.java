package netty.login.server.handler.outbound;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by suneee on 2018/11/20.
 */
public class OutboundHanlerA extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHanlerA: " + msg);
        super.write(ctx, msg, promise);
    }
}
