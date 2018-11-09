
package thread;

public class TestThread {
    public static int count = 0;

    public void inc() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        // 同时启动1000个线程，去进行i++计算，看看实际结果
        /*for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    TestThread.inc();
                }
            }).start();
        }*/
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    new TestThread().inc();
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {
                    new TestThread().inc();
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {
                    new TestThread().inc();
                }
            }).start();
        }
        /*// 休眠1秒，等待前面线程累加操作完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        //System.out.println("运行结果:Counter.count=" + TestThread.count);
    }
}