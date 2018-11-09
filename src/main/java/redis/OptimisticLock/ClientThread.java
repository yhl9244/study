package redis.OptimisticLock;

import util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import util.ObjectUtils;

import java.util.List;

/**
 * Created by suneee on 2018/11/1.
 */
public class ClientThread implements Runnable {

    private Jedis jedis = null;
    private String key = "productNum";
    private String clientlist = "clientlist";
    private String clientName;

    public ClientThread(int i) {
        this.clientName = "编号=" + i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 5000)); // 随机睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("顾客:" + clientName + "开始抢商品");
            jedis = JedisUtil.getInstance().getJedis();
            try {
                jedis.watch(key);
                int productNum = Integer.parseInt(jedis.get(key));
                if(productNum > 0) {
                    Transaction transaction = jedis.multi();
                    transaction.set(key, String.valueOf(productNum-1));
                    List<Object> result = transaction.exec();
                    if(ObjectUtils.isEmpty(result)){
                        System.err.println("顾客:" + clientName + "没有抢到");
                    }else{
                        jedis.sadd(clientlist, clientName);
                        System.out.println("顾客:" + clientName + "抢到商品");
                        break;
                    }
                }else {
                    System.err.println("库存为0,顾客:" + clientName + "没有抢到");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.unwatch();
                JedisUtil.getInstance().returnJedis(jedis);
            }
        }
    }
}
