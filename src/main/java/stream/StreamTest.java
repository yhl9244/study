package stream;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
    public void testSort1(){
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
    public void testSort2() {
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

    /**
     * 去重
     */
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

    /**
     * 过滤
     */
    @Test
    public void testFilter() {
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        List<User> users = new ArrayList<>();
        for (User userData : userDatas) {
            if(userData.getName().startsWith("韩")){
                users.add(userData);
            }
        }
        System.out.println("传统方式耗时:" + (System.currentTimeMillis() - start));
        users.forEach(user1 -> {
            System.out.println(user1);
        });

        List<User> userDatas1 = user.createUserDatas();
        start = System.currentTimeMillis();
        List<User> collect = userDatas1.stream().filter(user1 -> user1.getName().startsWith("韩")).collect(Collectors.toList());
        System.out.println("java8方式耗时:" + (System.currentTimeMillis() - start));
        collect.forEach(user1 -> {
            System.out.println(user1);
        });

        List<User> collect1 = userDatas1.stream().filter(user1 -> user1.getName().startsWith("韩") && user1.getGender() == 2)
                .collect(Collectors.toList());
        List<User> collect2 = userDatas.stream().filter(user1 -> user1.getName().startsWith("韩")).filter(user1 -> user1.getGender() == 2)
                .collect(Collectors.toList());
        List<User> collect3 = userDatas.stream().filter(user1 -> user1.getGender() == 2).filter(user1 -> user1.getName().startsWith("韩"))
                .collect(Collectors.toList());
        System.out.println("collect1->:" + JSON.toJSONString(collect1));
        System.out.println("collect2->:" + JSON.toJSONString(collect2));
        System.out.println("collect3->:" + JSON.toJSONString(collect3));
    }


    /**
     * 只列出名字和婚姻状况
     */
    @Test
    public void testMap(){
        List<User> userDatas = user.createUserDatas();
        List<String> result = new ArrayList<>();
        for (User userData : userDatas) {
            result.add(userData.getName() + ":" .concat(userData.isHasMarried() ? "已婚" : "未婚"));
        }
        result.forEach(re ->{
            System.out.println(re);
        });

        List<User> userDatas1 = user.createUserDatas();
        List<String> collect = userDatas1.stream().map(user1 -> user1.getName() + ":".concat(user1.isHasMarried() ? "已婚" : "未婚"))
                .collect(Collectors.toList());
        collect.forEach(rs ->{
            System.out.println(rs);
        });
    }

    /**
     * 判断当前数组是否包含某些特定元素
     */
    @Test
    public void testAnyMatch() {
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        boolean isChild = false;
        for (User userData : userDatas) {
            if(userData.getAge() < 18) {
                isChild = true;
                break;
            }
        }
        System.out.println("传统方式耗时:" + (System.currentTimeMillis() - start));
        System.out.println("isChild:" + isChild);

        List<User> userDatas1 = user.createUserDatas();
        start = System.currentTimeMillis();
        boolean anyMatch = userDatas1.stream().anyMatch(user1 -> user1.getAge() < 18);
        System.out.println("java8方式耗时:" + (System.currentTimeMillis() - start));
        System.out.println("anyMatch:" + anyMatch);
    }

    /**
     * 确认所有元素均满足某一条件
     */
    @Test
    public void testAllMatch() {
        List<User> userDatas = user.createUserDatas();
        long start = System.currentTimeMillis();
        boolean allMarried = true;
        for (User userData : userDatas) {
            if(!userData.isHasMarried()){
                allMarried = false;
                break;
            }
        }
        System.out.println("传统方式耗时:" + (System.currentTimeMillis() - start));
        System.out.println("allMarried:" + allMarried);

        List<User> userDatas1 = user.createUserDatas();
        start = System.currentTimeMillis();
        boolean allMatch = userDatas1.stream().allMatch(user1 -> user1.isHasMarried());
        System.out.println("java8方式耗时:" + (System.currentTimeMillis() - start));
        System.out.println("allMatch:" + allMatch);
    }

    /**
     * 求和求平均值
     */
    @Test
    public void testSum() {
        List<User> userDatas = user.createUserDatas();
        long sum = 0;
        for (User userData : userDatas) {
            sum += userData.getAge();
        }
        System.out.println("sum:" + sum);
        System.out.println("avg:" + sum/userDatas.size());

        List<User> userDatas1 = user.createUserDatas();
        sum = userDatas1.stream().mapToInt(user1 -> user1.getAge()).sum();
        OptionalDouble average = userDatas1.stream().mapToInt(user1 -> user1.getAge()).average();
        System.out.println("sum:" + sum);
        System.out.println("average:" + average.getAsDouble());
    }

    /**
     * 分组
     */
    @Test
    public void testGroupingby() {
        List<User> userDatas = user.createUserDatas();
        Map<Integer,List<User>> map = new HashMap<>();
        for (User userData : userDatas) {
            List<User> users = map.get(userData.getAge());
            if(CollectionUtils.isEmpty(users)){
                users = new ArrayList<>();
                map.put(userData.getAge(), users);
            }
            users.add(userData);
        }
        System.out.println("map:" +  JSON.toJSONString(map));

        List<User> userDatas1 = user.createUserDatas();
        Map<Integer, List<User>> collect = userDatas1.stream().collect(Collectors.groupingBy(user1 -> user1.getAge()));
        System.out.println("collect:" + JSON.toJSONString(collect));
    }

    /**
     * 链式操作
     */
    @Test
    public void testLinkOpreation() {
        List<User> userDatas = user.createUserDatas();
        userDatas.stream().filter(user1 -> user1.getGender() == 2).map(user1 -> user1.getName()).forEach(System.out::println);
    }
}
