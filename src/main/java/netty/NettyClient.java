package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.omg.CORBA.TIMEOUT;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by suneee on 2018/10/24.
 */
public class NettyClient {

    private final static Integer MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new StringEncoder());
                        // 自定义逻辑处理器
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        //Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
        //Channel channel = connect(bootstrap, "127.0.0.1", 8000).channel();
        // 增加重试机制
        Channel channel = connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY).channel();

        /*while (true) {
            channel.writeAndFlush("hello world : " + new Date());
            Thread.sleep(2000);
        }*/
    }

    private static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else if (retry == 0) {
                    System.err.println("重试次数已用完,放弃连接");
                } else {
                    // 第几次重连
                    int order = MAX_RETRY - retry + 1;
                    // 间隔时间
                    int delay = 1 << order;
                    System.err.println(new Date() +":连接失败, 第" + order + "次重连....");
                    //connect(bootstrap, host, port);
                    bootstrap.config().group().schedule(() -> connect(bootstrap,host,port, retry-1), delay, TimeUnit.SECONDS);
                }
            }
        });
        return channelFuture;
    }
}
