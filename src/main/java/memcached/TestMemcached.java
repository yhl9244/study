package memcached;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * Created by suneee on 2018/9/21.
 */
public class TestMemcached {

    public static void main(String[] args) throws IOException {
        //set();
        //add();
        //replace();
        //append();
        //cas();
        //delete();
        incrdecr();
    }

    /**
     * set操作
     */
    private static void set() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
//            System.out.println("Connection to server sucessful.");
            // 存储数据
            Future set = memcachedClient.set("runoob", 900, "runoob");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("runoob"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add操作
     */
    private static void add() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
//            System.out.println("Connection to server sucessful.");
            // 存储数据
            Future set = memcachedClient.add("runoob", 900, "test");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("runoob"));
            // 增加新的key
            set = memcachedClient.add("test", 900, "test");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("test"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * replace操作
     */
    private static void replace() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
//            System.out.println("Connection to server sucessful.");
            // 存储数据
            Future set = memcachedClient.replace("runoob", 900, "test");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("runoob"));
            // 增加新的key
            set = memcachedClient.replace("foo", 900, "test");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("test"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * append/prepend操作
     */
    private static void append() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
//            System.out.println("Connection to server sucessful.");
            // 存储数据
            Future set = memcachedClient.set("foo", 900, "bar");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("foo"));
            // 在后面拼接
            set = memcachedClient.append(1,"foo","after");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("foo"));
            // 在前面拼接
            set = memcachedClient.prepend(1,"foo","before");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("foo"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CAS操作
     */
    private static void cas() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
            // 存储数据
            Future set = memcachedClient.set("testcas", 900, "cas");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("testcas"));
            // 通过gets 获取 CAS token
            CASValue casValue = memcachedClient.gets("testcas");
            // 输出令牌
            System.out.println("输出令牌:" + casValue);
            // CAS修改值
            CASResponse cas = memcachedClient.cas("testcas", casValue.getCas(), "testcas");
            // 查看存储状态
            System.out.println("set status:" + cas);
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("testcas"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * delete操作
     */
    private static void delete() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
            // 存储数据
            Future set = memcachedClient.set("delete", 900, "delete");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("delete"));
            // 增加新的key
            set = memcachedClient.delete("delete");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("delete"));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * incr/decr 操作
     */
    private static void incrdecr() {
        try {
            // 连接Memcached服务
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("192.168.92.137", 11211));
            // 存储数据
            Future set = memcachedClient.set("number", 900, "1000");
            // 查看存储状态
            System.out.println("set status:" + set.get());
            // 输出值
            System.out.println("runoob value in cache - " + memcachedClient.get("number"));
            // 自增输出值
            System.out.println("runoob value in cache - " + memcachedClient.incr("number",111));
            // 自减输出值
            System.out.println("runoob value in cache - " + memcachedClient.decr("number",112));

            // 关闭连接
            memcachedClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
