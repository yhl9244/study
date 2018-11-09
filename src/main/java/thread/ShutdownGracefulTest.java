package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by suneee on 2018/11/9.
 */
public class ShutdownGracefulTest {

    public static void main(String[] args) {
        test();
        System.out.println("--------------------------");
        test();
        System.out.println("main method exit...");
        System.exit(0);
    }

    public static void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int id = i;
            Thread thread = new Thread(() -> {
                System.out.println(System.currentTimeMillis() + ": thread_" + id + " starting...");
                try {
                    TimeUnit.SECONDS.sleep(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": thread_" + id + " finish!");
            });
            thread.setDaemon(true);
            executorService.submit(thread);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
           // System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " No1 shutdown hooking...");
            boolean shutdown = true;
            try {
                executorService.shutdown();
                //System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() +  " shutdown signal got, wait threadPool finish.");
                while (!executorService.awaitTermination(1500, TimeUnit.SECONDS));
                boolean done = false;
               // System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() +  " all thread's done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                if(!executorService.isTerminated()){
                    executorService.shutdownNow();
                }
            }
            System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " No1 shutdown done...");
        }));

        /*Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " No2 shutdown hooking...");
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " No2 shutdown done...");
        }));*/
    }
}
