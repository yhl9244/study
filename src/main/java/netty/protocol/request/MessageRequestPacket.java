package netty.protocol.request;

import netty.protocol.Command;
import netty.protocol.Packet;

/**
 * Created by suneee on 2018/11/5.
 */
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
