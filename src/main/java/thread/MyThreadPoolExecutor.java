package thread;

import java.util.concurrent.*;

public class MyThreadPoolExecutor {

    /**
     * corePoolSize: 核心线程
     * maximumPoolSize: 最大线程
     * keepAliveTime: 空闲时间(超过核心线程数量空闲线程存活时间)
     * unit: 时间单位
     * workQueue: 等待队列
     * threadFactory: 线程工厂
     * handler: 拒绝策略
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5, 20, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024),
            new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /***********4种队列************/
    /***********4种拒绝策略************/
    // 无界队列 默认int最大值 会引起OOM
    private static ThreadPoolExecutor executor1 = new ThreadPoolExecutor(
            5, 20, 0L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1024),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return null;
                }// 线程池未关闭,运行被放弃任务
            }, new ThreadPoolExecutor.CallerRunsPolicy());
    // 直接提交队列 没有容量不会保存,直接创建新线程  new DelayQueue<>()
    // 有界队列
    private static ThreadPoolExecutor executor2 = new ThreadPoolExecutor(
            5, 20, 0L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1024),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return null;
                }// 丢弃最老的一个请求
            }, new ThreadPoolExecutor.DiscardOldestPolicy());
    // 优先级队列
    private static ThreadPoolExecutor executor3 = new ThreadPoolExecutor(
            5, 20, 0L, TimeUnit.SECONDS,
            new PriorityBlockingQueue<>(1024),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return null;
                }// 丢弃无法处理的任务,不做任务处理
            }, new ThreadPoolExecutor.DiscardPolicy());
    //实现 RejectedExecutionHandler 接口，并重写 rejectedExecution 方法即可。




}
