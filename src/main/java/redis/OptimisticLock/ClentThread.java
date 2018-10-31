package redis.OptimisticLock;

import redis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class ClentThread implements Runnable {

    private Jedis jedis = null;
    private String key = "productNum"; // 商品主键
    private String  clientList = "clientList"; // 抢到商品客户的列表主键
    private String clientName;

    public ClentThread(int num) {
        clientName = "编号=" + num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) Math.random()*5000); // 随机睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("顾客:" + clientName + "开始抢商品");
            jedis = JedisUtil.getInstance().getJedis();
            try{
                jedis.watch(key);
                int productNum = Integer.parseInt(jedis.get(key));
                if(productNum > 0) {
                    Transaction transaction = jedis.multi();
                    transaction.set(key, String.valueOf(productNum-1));
                    List<Object> result = transaction.exec();
                    if(result == null || result.isEmpty()) {
                        System.out.println("悲剧了，顾客:" + clientName + "没有抢到商品");// 可能是watch-key被外部修改，或者是数据操作被驳回
                    } else {
                        jedis.sadd(clientList,clientName); // 抢到商品记录一下
                        System.out.println("好高兴，顾客:" + clientName + "抢到商品");
                        break;
                    }
                } else {
                    System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
                    break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.unwatch();
                JedisUtil.getInstance().returnJedis(jedis);
            }
        }
    }
}
