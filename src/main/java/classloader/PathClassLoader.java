package classloader;

import java.io.*;

public class PathClassLoader extends ClassLoader {

    private String classpath;

    public PathClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getData(name);
        if (data == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, data, 0, data.length);
        }
    }

    private byte[] getData(String className) {
        String path = classpath + File.separator + className.replace(".", File.separator) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0 , num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new PathClassLoader("E:\\edu\\");
        Class<?> c = classLoader.loadClass("classloader.SelfClassloader");
        System.out.println(c.newInstance());
        System.out.println(c.getClassLoader());
    }
}
