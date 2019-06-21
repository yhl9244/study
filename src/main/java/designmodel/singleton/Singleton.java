package designmodel.singleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    private volatile static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
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
     * 序列化有问题
     * @return
     */
    private Object readResolve() {
        return singleton;
    }
}
