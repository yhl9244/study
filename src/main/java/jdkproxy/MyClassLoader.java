package jdkproxy;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    // 生成代理类加载路径
    private File dir;

    private String proxyClassPackage;

    public MyClassLoader(String path, String proxyClassPackage) {
        this.dir = new File(path);
        this.proxyClassPackage = proxyClassPackage;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (dir != null) {
            File classFile = new File(dir, name + ".class");
            if (classFile.exists()) {
                try {
                    byte[] bytes = FileCopyUtils.copyToByteArray(classFile);
                    return defineClass(proxyClassPackage + "." + name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 如果没有加载到，走默认加载方式
        return super.findClass(name);
    }

    public File getDir() {
        return dir;
    }

    public String getProxyClassPackage() {
        return proxyClassPackage;
    }
}
