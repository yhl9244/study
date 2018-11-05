package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.Packet;
import netty.protocol.PacketCodec;
import netty.serializer.Serializer;
import netty.serializer.impl.JsonSerializer;
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

        Serializer serializer = new JsonSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodec packetCodeC = new PacketCodec();
        ByteBuf byteBuf = packetCodeC.encode(ByteBufAllocator.DEFAULT,loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.encode(loginRequestPacket), serializer.encode(decodedPacket));

    }
}
