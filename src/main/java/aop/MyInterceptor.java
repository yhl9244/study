package aop;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

    private LoginService loginService;

    public MyInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行方法前");
        Object result = methodProxy.invoke(loginService, objects);
        System.out.println("执行方法后");
        return result;
    }
}
