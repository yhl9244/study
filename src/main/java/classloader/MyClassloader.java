package classloader;

import java.io.*;

/**
 * Created by suneee on 2018/9/6.
 */
public class MyClassloader extends ClassLoader {

    private String name;
    private String path;
    private String fileType = ".class";

    public MyClassloader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    public MyClassloader(String name) {
        super();
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name){
        byte[] data = loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try{
            this.name = this.name.replace(".", "\\");
            is = new FileInputStream(new File(this.path + name + fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())){
                baos.write(ch);
            }
            data = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args){
        MyClassloader loader1 = new MyClassloader("loader1");
        loader1.setPath("e:\\serlib\\");
        MyClassloader loader2 = new MyClassloader(loader1, "loader2");
        loader2.setPath("e:\\clilib\\");
        /*classloader.MyClassloader loader3 = new classloader.MyClassloader(null, "loader3");
        loader3.setPath("e:\\othlib\\");*/
        try{
            /*test(loader2);
            test(loader3);*/
            Class clazz = loader1.loadClass("classloader.Sample");
            Object o = clazz.newInstance();
            /*classloader.Sample sample = (classloader.Sample) o;
            System.out.println(sample.a);*/
//            Field field = clazz.getField("a");
//            System.out.println(field.getInt(o));
            System.out.println(clazz.hashCode());
            loader1 = null;
            clazz = null;
            o = null;
            loader1 = new MyClassloader("loader1");
            loader1.setPath("e:\\serlib\\");
            clazz = loader1.loadClass("classloader.Sample");
            System.out.println(clazz == loader1.loadClass("classloader.Sample"));
            System.out.println(clazz.hashCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void test(ClassLoader loader){

        Class<?> sample = null;
        try {
            sample = loader.loadClass("classloader.Sample");
            Object o = sample.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
