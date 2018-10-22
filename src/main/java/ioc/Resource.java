package ioc;

import java.io.InputStream;

/**
 * Created by suneee on 2018/9/17.
 */
public interface Resource {

    /**
     * 获取输入流
     * @return
     * @throws Exception
     */
    InputStream getInputstream() throws Exception;
}
