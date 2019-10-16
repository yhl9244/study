package classloader;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by suneee on 2018/9/6.
 */
public class TestClasscloader {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null != classLoader){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
        // 获取类加载器加载目录
        // BootStrap ClassLoader加载的文件
        System.out.println(System.getProperty("sun.boot.class.path"));
        // ExtClassLoader加载的文件
        System.out.println(System.getProperty("java.ext.dirs"));
        // AppClassLoader加载的文件
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.security.manager"));
    }
}
