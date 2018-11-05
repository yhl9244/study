package netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by suneee on 2018/11/5.
 */
public abstract class Packet {

    // 版本
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    // 指令
    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
