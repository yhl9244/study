package netty.protocol.response;

import netty.protocol.Command;
import netty.protocol.Packet;

/**
 * Created by suneee on 2018/11/5.
 */
public class LoginResponsePacket extends Packet {

    private boolean isSuccess;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
