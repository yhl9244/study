package netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import netty.attribute.Attributes;

/**
 * Created by suneee on 2018/11/5.
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.login).set(true);
    }

    public static Boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.login);
        return attr.get() != null;
    }
}
