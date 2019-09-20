package aop.self;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

public class MyMethodMatcher implements MethodMatcher {

    /*
     *  被监控接口比如（BaseService），没有重载方法
     *  每一个方法名称都是以唯一
     *  此时可以采用 static检测方式，只根据方法名称判断
     * 参数：method: 接口中某一个方法
     *     targetClass: 接口中一个实现类
     *
     *  业务：只想为Person类中play方法提供织入
     */
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        String methodName = method.getName();
        if ("play".equals(methodName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        return false;
    }
}
