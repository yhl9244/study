package netty.message.serialize;

import netty.message.serialize.impl.JsonSerialize;

public interface Serializer {

    Serializer DEFAULT = new JsonSerialize();

    /**
     * 序列化算法
     */
    byte getSerializeAlgorithm();

    /**
     * java对象转换为二进制
     */
    byte[] encode(Object o);

    /**
     * 二进制转换为java对象
     */
    <T> T decode(Class<T> clazz, byte[] bytes);
}
