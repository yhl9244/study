package aop.testAop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * Created by suneee on 2018/8/3.
 */
public class TestAdvisor implements PointcutAdvisor {

    /**
     * 获取切入点
     * @return
     */
    @Override
    public Pointcut getPointcut() {
        return new TestPointcut();
    }

    /**
     * 获取通知处理逻辑
     * @return
     */
    @Override
    public Advice getAdvice() {
        return new TestBeforeAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
