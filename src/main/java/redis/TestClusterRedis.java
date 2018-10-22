package redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by suneee on 2018/9/12.
 */
public class TestClusterRedis {

    public static void main(String[] args) throws IOException {
        // 端口
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.92.137", 7001));
        nodes.add(new HostAndPort("192.168.92.137", 7002));
        nodes.add(new HostAndPort("192.168.92.137", 7003));
        nodes.add(new HostAndPort("192.168.92.137", 7004));
        nodes.add(new HostAndPort("192.168.92.137", 7005));
        nodes.add(new HostAndPort("192.168.92.137", 7006));

        // config配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(20);
        config.setMaxWaitMillis(-1);
        config.setTestOnBorrow(true);

        JedisCluster jc = new JedisCluster(nodes,6000,100,config);

        /*System.out.println(jc.set("name","yhl"));
        System.out.println(jc.set("sex","man"));*/
        System.out.println(jc.get("sex"));
        System.out.println(jc.get("name"));
        jc.close();
    }
}
