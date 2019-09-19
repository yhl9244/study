package jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ManHandler implements InvocationHandler {

    private Man man;

    public ManHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(man, args);
        after();
        return null;
    }

    private void after() {
        System.out.println("after----");
    }

    private void before() {
        System.out.println("before....");
    }

    public static void main(String[] args) throws Throwable {
        Man man = new Yuanhuiliang();
        Man proxyMan = (Man) Proxy.newProxyInstance(man.getClass().getClassLoader(), new Class[]{Man.class}, new ManHandler(man));
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();

        // 打印JVM在内存中生成的动态代理类
        createProxyClassFile(Man.class);
    }

    private static void createProxyClassFile(Class c) {
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{c});
        // 写入文件
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
