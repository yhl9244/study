package netty.message.serialize.impl;

import com.alibaba.fastjson.JSON;
import netty.message.serialize.SerializerAlgorithm;
import netty.message.serialize.Serializer;

public class JsonSerialize implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
