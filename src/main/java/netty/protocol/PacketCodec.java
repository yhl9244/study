package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.request.MessageRequestPacket;
import netty.protocol.response.LoginResponsePacket;
import netty.protocol.response.MessageResponsePacket;
import netty.serializer.Serializer;
import netty.serializer.impl.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suneee on 2018/11/5.
 */
public class PacketCodec {

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetMap;

    private static final Map<Byte, Serializer> serializerMap;

    public static final PacketCodec INSTANCE = new PacketCodec();

    static {

        packetMap = new HashMap<>();
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(), new JsonSerializer());
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 获取ByteBuf
        ByteBuf buffer = byteBufAllocator.ioBuffer();
        // 序列化对象
        byte[] bytes = Serializer.DEFAULT.encode(packet);

        buffer.writeInt(MAGIC_NUMBER);
        buffer.writeByte(packet.getVersion());
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buffer.writeByte(packet.getCommand());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);

        return buffer;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据长度
        int len = byteBuf.readInt();
        // 数据
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if(requestType != null && serializer != null) {
            return serializer.decode(requestType, bytes);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetMap.get(command);
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

}
