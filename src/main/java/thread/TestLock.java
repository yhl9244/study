package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by suneee on 2018/9/10.
 */
public class TestLock {

    private Lock lock = new ReentrantLock();

    private void method(Thread threadName) {
        lock.lock();
        try {
            System.out.println("线程:" + threadName.getName() + "获得锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程:" + threadName.getName() + "释放锁");
            lock.unlock();
        }
    }

    private void method1(Thread threadName) {
        if (lock.tryLock()) {
            try {
                System.out.println("线程:" + threadName.getName() + "获得锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程:" + threadName.getName() + "释放锁");
                lock.unlock();
            }
        } else {
            System.out.println("线程:" + threadName.getName() + "获取锁失败");
        }
    }

    public static void main(String[] args) {
        final TestLock testLock = new TestLock();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                //testLock.method(Thread.currentThread());
                testLock.method1(Thread.currentThread());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                //testLock.method(Thread.currentThread());
                testLock.method1(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
