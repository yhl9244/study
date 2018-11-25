package netty.message.protocol;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
