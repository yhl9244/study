package aop;

import net.sf.cglib.proxy.Enhancer;

public class ProxyFactory {

    public static LoginService createProxy(LoginService loginService){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LoginService.class);
        enhancer.setCallback(new MyInterceptor(loginService));
        return (LoginService) enhancer.create();
    }
}
