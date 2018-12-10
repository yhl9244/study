package netty.message.protocol.response;

import lombok.Data;
import netty.message.protocol.Packet;

import static netty.message.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserid;
    private String fromUsername;
    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromUserid() {

        return fromUserid;
    }

    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
