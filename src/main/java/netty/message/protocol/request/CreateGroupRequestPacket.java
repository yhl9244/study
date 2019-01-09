package netty.message.protocol.request;

import lombok.Data;
import netty.message.protocol.Packet;

import java.util.List;

import static netty.message.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * Created by suneee on 2019/1/9.
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
