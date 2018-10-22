package classloader;

import javax.sound.midi.Soundbank;

/**
 * Created by suneee on 2018/9/6.
 */
public class Dog {

    public Dog() {
        System.out.println("classloader.Dog class loader:" + this.getClass().getClassLoader());
    }
}
