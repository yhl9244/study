package aop.self;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        BaseService service = (BaseService) ac.getBean("personFactory");
        service.play();
        System.out.println("===========下面是没有进行代理的方法================");
        service.eat();
    }
}
