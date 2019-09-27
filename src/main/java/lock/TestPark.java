package lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestPark {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> testsync());
        t1.setName("t1");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main....1");
        LockSupport.unpark(t1);
    }

    public static void testsync() {
        System.out.println("t1...1");
        LockSupport.park();

        System.out.println("t1....2");
    }
}
