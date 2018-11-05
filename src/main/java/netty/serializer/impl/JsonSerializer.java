package netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import netty.serializer.Serializer;

/**
 * Created by suneee on 2018/11/5.
 */
public class JsonSerializer implements Serializer {

    @Override
    public Byte getSerializerAlgorithm() {
        return netty.serializer.SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T decode(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
