package netty.message.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.message.protocol.command.Command;
import netty.message.protocol.request.LoginRequestPacket;
import netty.message.protocol.request.MessageRequestPacket;
import netty.message.protocol.response.LoginResponsePacket;
import netty.message.protocol.response.MessageResponsePacket;
import netty.message.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetMap;
    private final Map<Byte, Serializer> serializeMap;

    private PacketCodeC() {
        packetMap = new HashMap<>();
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializeMap = new HashMap<>();
        serializeMap.put(Serializer.DEFAULT.getSerializeAlgorithm(), Serializer.DEFAULT);
    }

    public void encode(ByteBuf byteBuf, Packet packet){
        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.encode(packet);
        // 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet){
        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.encode(packet);
        // 编码
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        // 命令
        byte command = byteBuf.readByte();
        // 数据长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetType = getPacketType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if(serializer != null && packetType != null){
            return serializer.decode(packetType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializeMap.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getPacketType(byte command) {
        return packetMap.get(command);
    }
}
