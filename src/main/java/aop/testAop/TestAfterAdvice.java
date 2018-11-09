package aop.testAop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by suneee on 2018/8/3.
 */
public class TestAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("after " + target.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
