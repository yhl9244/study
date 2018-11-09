package aop;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class MyAspectTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        LoginService bean = (LoginService) context.getBean("loginService");
        bean.login("123");
    }
}
