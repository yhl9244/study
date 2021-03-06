package netty.login.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * Created by suneee on 2018/11/5.
 */
public class NettyServer {

    private static final int port = 8000;

    public static void main(String[] args) {
        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new ServerHandler());
                        // 处理读数据逻辑链
                        //ch.pipeline().addLast(new InboundHandlerA());
                        //ch.pipeline().addLast(new InboundHandlerB());
                        //ch.pipeline().addLast(new InboundHandlerC());

                        // 处理写数据逻辑链
                        //ch.pipeline().addLast(new OutboundHanlerA());
                        //ch.pipeline().addLast(new OutboundHanlerB());
                        //ch.pipeline().addLast(new OutboundHanlerC());

                        //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        //ch.pipeline().addLast(new LifeCrcleTestHandler());
                        /*ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new PacketEncoder());*/

                    }
                });
        bind(serverBootstrap, port);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap,port+1);
                }
            }
        });
    }

}
