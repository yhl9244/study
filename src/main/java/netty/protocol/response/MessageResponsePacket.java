package netty.protocol.response;

import netty.protocol.Command;
import netty.protocol.Packet;

/**
 * Created by suneee on 2018/11/5.
 */
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
