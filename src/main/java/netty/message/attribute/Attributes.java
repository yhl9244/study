package netty.message.attribute;

import io.netty.util.AttributeKey;
import netty.message.session.Session;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
