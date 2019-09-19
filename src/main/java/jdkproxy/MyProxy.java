package jdkproxy;

import org.springframework.util.FileCopyUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {

    private static final String rt = "\r";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler h) throws IllegalArgumentException {
        if (h == null) {
            throw new NullPointerException();
        }
        // 根据接口构造代理类$Proxy0
        Method[] methods = interfaces[0].getMethods();
        StringBuilder proxyClassString = new StringBuilder();
        proxyClassString.append("package ")
                .append(classLoader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces[0].getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h){").append(rt).append("this.h = h;}").append(rt)
                .append(getMethodString(methods, interfaces[0])).append("}");
        // 写入java文件，进行编译
        String fileName = classLoader.getDir() + File.separator + "$MyProxy0.java";
        File myProxyFile = new File(fileName);
        try {
            // 编译文件
            compile(proxyClassString, myProxyFile);
            // 利用自己的类加载器加载
            Class $Proxy0 = classLoader.findClass("$MyProxy0");
            // 利用自己的代理类初始化
            Constructor constructor = $Proxy0.getConstructor(MyInvocationHandler.class);
            Object o = constructor.newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMethodString(Method[] methods, Class anInterface) {
        StringBuffer methodStringBuffer = new StringBuffer();
        for (Method method : methods) {
            methodStringBuffer.append("public void ").append(method.getName())
                    .append("()").append("throws Throwable{ ")
                    .append("Method method1 = ").append(anInterface.getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\", new Class[]{});")
                    .append("this.h.invoke(this, method1, null);}").append(rt);
        }
        return methodStringBuffer.toString();
    }

    private static void compile(StringBuilder proxyClassString, File myProxyFile) throws IOException {
        FileCopyUtils.copy(proxyClassString.toString().getBytes(),  myProxyFile);

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

        Iterable javaFileObjects = standardFileManager.getJavaFileObjects(myProxyFile);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);

        task.call();

        standardFileManager.close();
    }
}
