package netty.message.protocol.response;

import lombok.Data;
import netty.message.protocol.Packet;
import netty.message.protocol.command.Command;

import static netty.message.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * Created by suneee on 2019/1/9.
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
