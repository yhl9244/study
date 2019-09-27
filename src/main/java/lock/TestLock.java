package lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> testsync());
        t1.setName("t1");

        Thread t2 = new Thread(() -> testsync());
        t2.setName("t2");

        t1.start();
        t2.start();

        //System.out.println("main");
    }

    public static void testsync() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
