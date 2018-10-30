package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy(exposeProxy = true)
public class LoginAspect {

    @Pointcut(value = "execution(* *..LoginServiceImpl.login(..))")
    public void jointPoint(){

    }

    /*@Before("execution(* *..LoginServiceImpl.login(..))")
    public void before() {
        System.out.println("前置增强");
    }*/

    /*@Before("execution(* *..LoginServiceImpl.login(..))")
    public void beforeJoinPoint(JoinPoint joinPoint) {
        System.out.println("切入点表达式:" + joinPoint);
        System.out.println("切入点签名:" + joinPoint.getSignature());
        System.out.println("切入点目标对象:" + joinPoint.getTarget());

        Object[] args = joinPoint.getArgs();
        if(args.length > 0) {
            System.out.println("参数:");
            for (Object arg : args) {
                System.out.println(arg + " ");
            }
        }
    }*/


    /*@AfterReturning(value = "execution(* *..LoginServiceImpl.login(..))", returning = "result")
    public void AfterReturning(Object result) {
        System.out.println("后置增强");
        System.out.println("执行结果:" + result);
    }*/

    //@Around("execution(* *..LoginServiceImpl.login(..))")
    @Around(value = "jointPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        Object proceed = joinPoint.proceed();
        System.out.println("环绕通知后....");
        return proceed;

    }

    @AfterThrowing(value = "execution(* *..LoginServiceImpl.login(..))", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println("异常通知:" + e.getMessage());
    }

    //@After("execution(* *..LoginServiceImpl.login(..))")
    @After(value = "jointPoint()")
    public void adter() {
        System.out.println("最终方法执行");
    }

    @AfterReturning(value = "execution(* *..LoginServiceImpl.isLegal(..))", returning = "result")
    public void isLeageAspect(JoinPoint joinPoint, Object result) {
        if(!(Boolean) result) {
            Object[] args = joinPoint.getArgs();
            String useId = (String) args[0];
            System.out.println("增加非法用户" + useId + "登录次数" );
        }
    }

}
