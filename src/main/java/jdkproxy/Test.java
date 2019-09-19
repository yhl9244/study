package jdkproxy;

public class Test {

    public static void main(String[] args) throws Throwable {
        Man man = new Yuanhuiliang();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader("E:\\edu\\study\\src\\main\\java\\jdkproxy", "jdkproxy"),
                new Class[]{Man.class}, myHandler);
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();
    }
}
