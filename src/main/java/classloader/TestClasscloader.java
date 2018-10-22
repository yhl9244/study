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
    }
}
