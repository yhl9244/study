package singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton implements Serializable, Cloneable{
    private volatile static Singleton singleton;
    private Singleton (){}
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 反射破坏单例
     */
    public static void reflectTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Singleton s1 = Singleton.getSingleton();

        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton s2 = constructor.newInstance();
        System.out.println(s1 == s2);
    }

    /**
     * 反序列化破坏单例
     */
    public static void serializeTest() throws IOException, ClassNotFoundException {
        // 写文件
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tempFile"));
        outputStream.writeObject(Singleton.getSingleton());
        // 读文件
        File file = new File("tempFile");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Singleton s1 = (Singleton) inputStream.readObject();
        // 判断对象是否相同
        System.out.println(s1 == Singleton.getSingleton());
    }

    /**
     * 克隆破坏单例
     */
    public static void cloneTest() throws CloneNotSupportedException {
        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = (Singleton) s1.clone();
        System.out.println(s1 == s2);
    }

    public static void main(String[] args) {
        try {
            //reflectTest();
            //cloneTest();
            serializeTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}