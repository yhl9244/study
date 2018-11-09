package aop.testAop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by suneee on 2018/8/3.
 */
public class TestBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("before " + target.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
