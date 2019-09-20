package aop.self;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class MyPointCutAdvisor implements PointcutAdvisor {

    private Pointcut pointcut;//当前拦截对象和对象调用主要业务方法 person对象.play()

    private  Advice advice; //次要业务以及次要业务与主要业务执行顺序

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
