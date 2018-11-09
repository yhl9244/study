package classloader;

/**
 * Created by suneee on 2018/9/6.
 */
public class Sample {

    public static int a = 1;

    public Sample() {
        System.out.println("classloader.Sample class loader:" + this.getClass().getClassLoader());
        new Dog();
    }
}
