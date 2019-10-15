package jvm.init;

public class ConstantClass {

    static {
        System.out.println("ConstantClass static");
    }

    public static final String HELLOWORLD = "hello world";
}
