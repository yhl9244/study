package thread;

import netty.protocol.PacketCodec;

import java.util.concurrent.*;

public class MyTask implements Runnable {

    String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("正在执行: Thread ID :" + Thread.currentThread().getId()
        + ", Task Name:" + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 固定数量线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 单线程池
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 缓存线程池
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(myTask);
        }
        executorService.shutdown();*/

        ExecutorService executorService = new ThreadPoolExecutor(5,5,0L,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()){

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成： " + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask("TASK-GEYM-" + i);
            executorService.execute(myTask);
            Thread.sleep(10);

        }
        executorService.shutdown();
    }
}
