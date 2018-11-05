package netty.login.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.protocol.PacketCodec;
import netty.protocol.request.MessageRequestPacket;
import netty.util.LoginUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by suneee on 2018/11/5.
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String host = "127.0.0.1";
    private static final int port = 8000;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap,host, port, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int maxRetry) {
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()) {
                    System.out.println(new Date() + ":连接成功, 启动控制台线程...");
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsoleThread(channel);
                }else if(maxRetry == 0) {
                    System.err.println("连接次数已用完.放弃连接");
                }else{
                    int order = (MAX_RETRY - maxRetry) + 1;
                    int delay = 1 << order;
                    System.err.println(new Date() + ":连接失败, 第" + order + "次重新连接");
                    bootstrap.config().group().schedule(()->{
                        connect(bootstrap,host,port,maxRetry-1);
                    },delay, TimeUnit.SECONDS);
                }
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() ->{
            while (!Thread.interrupted()){
                if(LoginUtil.hasLogin(channel)){
                    System.out.println("输入消息并发送到服务器端:");
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(s);

                    ByteBuf encode = PacketCodec.INSTANCE.encode(channel.alloc(), messageRequestPacket);
                    channel.writeAndFlush(encode);
                }
            }
        }).start();
    }
}
