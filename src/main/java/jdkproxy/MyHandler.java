package jdkproxy;

import java.lang.reflect.Method;

public class MyHandler implements MyInvocationHandler {


    private Man man;

    public MyHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        before();
        Object invoke = method.invoke(man, args);
        after();
        return invoke;
    }

    private void after() {
        System.out.println("after----");
    }

    private void before() {
        System.out.println("before....");
    }
}
