package netty.message.client.console;

import io.netty.channel.Channel;
import netty.message.util.SessionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by suneee on 2019/1/9.
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private static Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        //consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGrouprConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        if(!SessionUtil.hasLogin(channel)){
            return;
        }

        // 获取第一个指令
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(null != consoleCommand) {
            consoleCommand.exec(scanner,channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
