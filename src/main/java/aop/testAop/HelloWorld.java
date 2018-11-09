package aop.testAop;

/**
 * Created by suneee on 2018/8/3.
 */
public class HelloWorld {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("hello" + name);
    }
}
