package netty.message.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by suneee on 2019/1/9.
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
