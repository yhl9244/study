package netty.serializer;

import netty.serializer.impl.JsonSerializer;

/**
 * Created by suneee on 2018/11/5.
 */
public interface Serializer {

    Byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JsonSerializer();

    // 序列化算法
    Byte getSerializerAlgorithm();

    // 编码
    byte[] encode(Object object);

    // 解码
    <T> T decode(Class<T> clazz, byte[] bytes);
}
