package netty.message.protocol.request;

import netty.message.protocol.Packet;
import static netty.message.protocol.command.Command.LOGOUT_REQUEST;

/**
 * Created by suneee on 2019/1/9.
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
