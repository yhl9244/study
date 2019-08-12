package singleton;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class SingletonDemo {

    private static final SingletonDemo instance = new SingletonDemo();

    private	SingletonDemo(){
        if(instance != null) {
            return;
        }
    }

    public static SingletonDemo getInstance() {
        return instance;
    }



    @Override
    // clone解决单例方法
    protected Object clone() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        Constructor<SingletonDemo> constructor = SingletonDemo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonDemo s1 = constructor.newInstance();
        SingletonDemo s2 = constructor.newInstance();
    }

}