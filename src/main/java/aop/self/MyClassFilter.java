package aop.self;

import org.springframework.aop.ClassFilter;

public class MyClassFilter implements ClassFilter {

    /*
     *  1.一个接口下会有多个实现类
     *  2.判断当前实现类是不是我们织入方式关心的目标类
     *  BaseService接口我们现在只想管理Person.
     *  参数：就是当前被拦截类：可能Person，可能Gog
     * */
    @Override
    public boolean matches(Class<?> aClass) {
        if (aClass == Person.class) {
            return true;
        }
        return false;
    }
}
