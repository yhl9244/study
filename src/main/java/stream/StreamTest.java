package stream;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by suneee on 2018/11/8.
 */
public class StreamTest {

    private static User user = new User();

    /**
     * 按照年龄排序
     */
    @Test
    public void test1(){
        System.out.println("-------------传统方式排序-----------");
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        Collections.sort(userDatas, user.getAgeComparator());
        System.out.println("传统方式排序耗时:" + (System.currentTimeMillis() - start));
        userDatas.stream().forEach(user1 -> {
            System.out.println(user1);
        });

        System.out.println("-------------Java8方式排序-----------");
        List<User> userDatas2 = user.createUserDatas();
        long s = System.currentTimeMillis();
        List<User> collect = userDatas2.stream().sorted(user.getAgeComparator()).collect(Collectors.toList());
        System.out.println("java8方式排序耗时:" + (System.currentTimeMillis() - s));
        collect.stream().forEach(user1 -> {
            System.out.println(user1);
        });
    }

    /**
     * 选出年龄最小的3个人
     */
    @Test
    public void test2() {
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        Collections.sort(userDatas, user.getAgeComparator());
        List<User> users = userDatas.subList(0, 3);
        System.out.println("传统方式耗时:" + (System.currentTimeMillis() - start));
        users.stream().forEach(user1 -> {
            System.out.println(user1);
        });

        List<User> userDatas1 = user.createUserDatas();
        start = System.currentTimeMillis();
        List<User> collect = userDatas1.stream().sorted(user.getAgeComparator()).limit(3).collect(Collectors.toList());
        System.out.println("java8方式耗时:" + (System.currentTimeMillis() - start));
        collect.forEach(user1 -> {
            System.out.println(user1);
        });
    }

    @Test
    public void testDistinct() {
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        int size = userDatas.size();
        for (int i = 1; i < size; i++) {
            if(userDatas.get(i).equals(userDatas.get(i-1))){
                userDatas.remove(userDatas.get(i));
                i--;
                size--;
            }
        }
        System.out.println("传统方式耗时:" + (System.currentTimeMillis() - start));
        userDatas.forEach(user1 -> {
            System.out.println(user1);
        });

        List<User> userDatas1 = user.createUserDatas();
        start = System.currentTimeMillis();
        List<User> collect = userDatas1.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println("java8方式耗时:" + (System.currentTimeMillis() - start));
        collect.forEach(user1 -> {
            System.out.println(user1);
        });
    }
}
