package aop.testAop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by suneee on 2018/8/3.
 */
public class Test {

    public static void main(String[] args) {
        // 原生方法
//        HelloWorld helloWorld = new HelloWorld();
//        helloWorld.setName("yhl");
//        helloWorld.sayHello();

        // spring IOC
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        HelloWorld helloWorld = (HelloWorld) context.getBean("helloworld");
//        helloWorld.sayHello();

        // spring AOP
        Target target = (Target) context.getBean("testAop");
        target.test();
        System.out.println("-----------------------");
        target.test2();
    }
}
