package netty.message.util;

import java.util.UUID;

/**
 * Created by suneee on 2019/1/9.
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
