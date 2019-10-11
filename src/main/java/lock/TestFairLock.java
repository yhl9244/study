package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TestFairLock {

    private ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
        final TestFairLock testFairLock = new TestFairLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("线程ID" + Thread.currentThread().getName() + "进入");
                testFairLock.testLock();
            });
        }
        executorService.shutdown();
    }

    public void testLock() {
        try {
            fairLock.lock();
            Thread.sleep(1000);
            System.out.println("线程ID" + Thread.currentThread().getName() + "获取锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }
}
