package lock.happenbefore;

public class SynchronizedExample {
    private int x = 0;

    public void synBlock(){
        // 1.加锁
        synchronized (SynchronizedExample.class){
            x = 1; // 对x赋值
        }
        // 3.解锁
    }

    // 1.加锁
    public synchronized void synMethod(){
        x = 2; // 对x赋值
    }
    // 3. 解锁
}