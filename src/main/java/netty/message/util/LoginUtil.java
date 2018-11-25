package netty.message.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import netty.message.attribute.Attributes;

public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}
