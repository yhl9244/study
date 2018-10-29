package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        // 任务没有完成,调度也不会启动
        /*scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    // 任务执行时间大于间隔时间,已执行时间为准(防止任务堆积)
                    Thread.sleep(10000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }// initialDelay(初始延迟) 第一次延时时间; period 表示间隔时间
        }, 0, 2, TimeUnit.SECONDS);*/

        /*scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    // 任务执行时间大于间隔时间,已执行时间为准(防止任务堆积)
                    Thread.sleep(10000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }// initialDelay(初始延迟) 延时时间; delay + 任务执行时间 = 表示间隔时间 period
        },0, 2, TimeUnit.SECONDS);*/

        // 给定时间.对任务进行一次调度
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("5秒后执行....");
            }
        }, 5, TimeUnit.SECONDS);


    }
}
