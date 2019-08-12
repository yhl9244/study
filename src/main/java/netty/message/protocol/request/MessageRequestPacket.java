package netty.message.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import netty.message.protocol.Packet;

import static netty.message.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserid;
    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    public MessageRequestPacket(String toUserid, String message) {
        this.toUserid = toUserid;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getToUserid() {
        return toUserid;
    }

    public void setToUserid(String toUserid) {
        this.toUserid = toUserid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
