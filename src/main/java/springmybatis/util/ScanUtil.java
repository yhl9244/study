package springmybatis.util;

import springmybatis.anno.Autowired;
import springmybatis.anno.Compoment;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suneee on 2018/10/9.
 */
public class ScanUtil  {

    public static Map<String, Object> map = new HashMap<String, Object>();

    public static void doScan() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String packagePath = "F:\\testSolr\\src\\main\\java\\springmybatis";
        File file = new File(packagePath);
        String[] list = file.list();
        for (String s : list) {
            String classFile = packagePath + "\\" + s;
            File file1 = new File(classFile);
            String[] list1 = file1.list();
            for (String s1 : list1) {
                String className = "springmybatis" + "." + s + "." +  s1.substring(0,s1.indexOf("."));
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(Compoment.class)){
                    Object o = clazz.newInstance();
                    map.put(clazz.getSimpleName(), o);
                }
            }
        }
        //System.out.println(map);
        // 自动注入
        for (Map.Entry<String, Object> entry : map.entrySet()){
            Object o = entry.getValue();
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.isAnnotationPresent(Autowired.class)){
                    for (Map.Entry<String, Object> entry1 : map.entrySet()) {
                        if(entry1.getKey().equals(field.getType().getSimpleName())){
                            field.setAccessible(true);
                            field.set(o,map.get(field.getType().getSimpleName()));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        doScan();
    }
}
