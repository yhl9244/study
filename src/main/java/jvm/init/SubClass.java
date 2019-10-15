package jvm.init;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass static");
    }

    static int a = 10;

    public SubClass() {
        System.out.println("SubClass init");
    }
}
