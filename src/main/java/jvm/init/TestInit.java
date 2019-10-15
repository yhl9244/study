package jvm.init;

public class TestInit {

    public static void main(String[] args) {
        //System.out.println(SubClass.value);
        //System.out.println(SubClass.a);
        //System.out.println(new SubClass().value);
        // 数组定义来引用类，不会触发此类的初始化
        //SubClass[] sca = new SubClass[10];
        // 调用类的常量,不会触发定义常量的类的初始化
        //System.out.println(ConstantClass.HELLOWORLD);
        // 静态语句块只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问
    }
}
