package mybatis.TestMybatis.Test;

import mybatis.TestMybatis.anno.Select;
import mybatis.TestMybatis.config.AppConfig;
import mybatis.TestMybatis.dao.TestAccounDao;
import mybatis.TestMybatis.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suneee on 2018/10/8.
 */
public class Test1 {

    public static void main(String[] args) throws Exception {
        init();
        TestAccounDao dao = (TestAccounDao) Proxy.newProxyInstance(TestAccounDao.class.getClassLoader(), new Class[]{TestAccounDao.class}, new AccountDaoInvocationHandler(new DefaultSqlSession()));
        dao.query(1);
    }


    public static Map<String, String> map = new HashMap<String, String>();
    public static void init() throws Exception {
        Class<?> clazz = Class.forName("mybatis.TestMybatis.dao.TestAccounDao");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Select.class)){
                String value = method.getAnnotation(Select.class).value();
                map.put(method.getDeclaringClass().getName()+ "." + method.getName(), value);
            }
        }
        System.out.println(map);
    }
}
