package netty.message.client.console;

import io.netty.channel.Channel;
import netty.message.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * Created by suneee on 2019/1/9.
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个用户:");
        String toUseId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUseId, message));
    }
}
