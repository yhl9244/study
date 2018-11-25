package netty.message.serialize.impl;

import com.alibaba.fastjson.JSON;
import netty.message.serialize.SerializerAlgorithm;
import netty.message.serialize.Serializer;

public class JsonSerialize implements Serializer {

    @Override
    public byte getSerializeAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] encode(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public <T> T decode(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
