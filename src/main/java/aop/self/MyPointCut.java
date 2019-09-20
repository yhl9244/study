package aop.self;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class MyPointCut implements Pointcut {

    private MyClassFilter classFilter;

    private MyMethodMatcher methodMatcher;


    @Override
    public ClassFilter getClassFilter() {
        return this.classFilter;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this.methodMatcher;
    }

    public void setClassFilter(MyClassFilter classFilter) {
        this.classFilter = classFilter;
    }

    public void setMethodMatcher(MyMethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}


