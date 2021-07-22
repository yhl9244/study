package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("spring");
        TestService2 testConfig = context.getBean(TestService2.class);
        testConfig.hello();;
    }
}
