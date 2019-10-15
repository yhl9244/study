package jvm.init;

public class SuperClass extends SSClass {
    static {
        value = 0;
        //System.out.println("SuperClass static:" + value); //编译器报错Illegal forward reference
        System.out.println("SuperClass static");
    }

    public static int value = 123;

    public SuperClass() {
        System.out.println("SuperClass init");
    }
}
