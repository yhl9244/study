package netty.message.client.console;

import io.netty.channel.Channel;
import netty.message.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * Created by suneee on 2019/1/9.
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
