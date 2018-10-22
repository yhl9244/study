package mybatis.TestMybatis.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by suneee on 2018/10/8.
 */
public class AccountDaoInvocationHandler implements InvocationHandler {

    public DefaultSqlSession sqlSession;

    public AccountDaoInvocationHandler(DefaultSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello");
        //method.invoke(proxy);
        sqlSession.selectList();
        return proxy;
    }
}
