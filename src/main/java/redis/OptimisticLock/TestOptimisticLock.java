package redis.OptimisticLock;

import redis.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestOptimisticLock {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        initProduct();
        initClient();
        printResult();

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间： "+(end-start)+"ms");
    }

    /**
     * 初始化商品个数
     */
    private static void initProduct() {
        int pruductNum = 100; // 模拟商品个数
        String key = "productNum";
        String clientList = "clientList"; // 抢到商品客户列表
        Jedis jedis = JedisUtil.getInstance().getJedis();

        if(jedis.exists(key)) {
            jedis.del(key);
        }
        if(jedis.exists(clientList)) {
            jedis.del(clientList);
        }
        jedis.set(key, String.valueOf(pruductNum)); // 初始化商品个数
        JedisUtil.getInstance().returnJedis(jedis);

    }

    /*
     * 初始化顾客开始抢商品
     */
    private static void initClient() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 模拟客户数目
        int clientNum = 1000;
        for (int i = 0; i < clientNum; i++) {
            executorService.execute(new ClentThread(i));
        }

        executorService.shutdown();

        while (true) {
            if(executorService.isTerminated()) {
                System.out.println("所有线程都结束了.....");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printResult() {
        Jedis jedis = JedisUtil.getInstance().getJedis();
        Set<String> clientList = jedis.smembers("clientList");
        int i = 0;
        for (String s : clientList) {
            System.out.println("第" + i++ + "个抢到商品，"+s + " ");
        }

        JedisUtil.getInstance().returnJedis(jedis);
    }
}
