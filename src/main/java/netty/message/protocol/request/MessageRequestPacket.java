package netty.message.protocol.request;

import netty.message.protocol.Packet;
import netty.message.protocol.command.Command;
import netty.message.protocol.response.MessageResponsePacket;

public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(){}

    public MessageRequestPacket(String message) {
        this.message = message;
    }

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
