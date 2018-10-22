package ioc;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by suneee on 2018/9/17.
 */
public class ResourceUrl implements Resource {

    /**
     * 类库URL
     */
    private final URL url;

    /**
     * 需要一个类库URL
     */
    public ResourceUrl(URL url) {
        this.url = url;
    }

    /**
     * 从URL中获取输入流
     */
    public InputStream getInputstream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();

    }

}
