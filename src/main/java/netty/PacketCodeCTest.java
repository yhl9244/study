package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.message.protocol.Packet;
import netty.message.protocol.PacketCodeC;
import netty.message.protocol.request.LoginRequestPacket;
import netty.message.serialize.Serializer;
import netty.message.serialize.impl.JsonSerialize;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {

   /* public static void main(String[] args) {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte) 1);
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("yhl");
        loginRequestPacket.setPassword("yhl");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf encode = packetCodeC.encode(loginRequestPacket);
        Packet decode = packetCodeC.decode(encode);

        System.out.println(serializer.serializer(loginRequestPacket));
        System.out.println(serializer.serializer(decode));
    }*/

    @Test
    public void encode() {

        Serializer serializer = new JsonSerialize();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ByteBufAllocator.DEFAULT,loginRequestPacket);
        Packet decodedPacket = PacketCodeC.INSTANCE.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
