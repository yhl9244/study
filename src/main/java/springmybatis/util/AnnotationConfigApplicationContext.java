package springmybatis.util;

import java.util.Map;

/**
 * Created by suneee on 2018/10/9.
 */
public class AnnotationConfigApplicationContext {

    public Object getBean(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ScanUtil.doScan();
        Object o = ScanUtil.map.get(className);
        if(o != null) {
            return o;
        }else {
            throw new ClassNotFoundException();
        }
    }
}
