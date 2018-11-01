package redis.OptimisticLock;

import util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by suneee on 2018/11/1.
 */
public class TestOptimisticLock {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        initProduct();
        initClient();
        printResult();

        long end = System.currentTimeMillis();

        System.out.println("程序运行时间:" + (end-start) + "ms");
    }

    private static void initProduct() {
        int productNum = 10;
        String key = "productNum";
        String clientList = "clientList";

        Jedis jedis = JedisUtil.getInstance().getJedis();
        if(jedis.exists(key)){
            jedis.del(key);
        }
        if(jedis.exists(clientList)) {
            jedis.del(clientList);
        }

        jedis.set(key, String.valueOf(productNum));
        JedisUtil.getInstance().returnJedis(jedis);
    }

    private static void initClient() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int clientNum = 100;
        for (int i = 0; i < clientNum; i++) {
            executorService.execute(new ClientThread(i));
        }

        executorService.shutdown();

        while (true) {
            if(executorService.isTerminated()) {
                System.out.println("所有线程执行完毕");
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

        int i = 0 ;
        Set<String> clientlist = jedis.smembers("clientlist");
        for (String s : clientlist) {
            System.out.println("第" + (i++) + "抢到商品," + s + " ");
        }
        JedisUtil.getInstance().returnJedis(jedis);
    }
}
