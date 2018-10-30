package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJdkTest {

    public static void main(String[] args){
        LoginService target = new LoginServiceImpl();

        LoginService proxy = (LoginService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("login登录前.....");
                        Object result = method.invoke(target, args);
                        System.out.println("login登录后.....");
                        return result;
                    }
                });
        proxy.login("admin");
        proxy.login("123");
    }
}
