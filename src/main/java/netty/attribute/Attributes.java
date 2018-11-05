package netty.attribute;

import io.netty.util.AttributeKey;

/**
 * Created by suneee on 2018/11/5.
 */
public interface Attributes {

    AttributeKey<Boolean> login = AttributeKey.newInstance("login");
}
