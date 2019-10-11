package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TestNoFairLock {

    private ReentrantLock nonFairLock = new ReentrantLock(false);

    public static void main(String[] args) {
        final TestNoFairLock testFairLock = new TestNoFairLock();
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
            nonFairLock.lock();
            Thread.sleep(1000);
            System.out.println("线程ID" + Thread.currentThread().getName() + "获取锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nonFairLock.unlock();
        }
    }
}
