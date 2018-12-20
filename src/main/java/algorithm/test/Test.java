package algorithm.test;

/**
 * Created by suneee on 2018/12/20.
 */
public class Test {

    public static void main(String[] args) {
        //test(100);
        for (int i = 1; i <= 10; i++) {
            System.out.print(fib(i) + "  ");
        }

    }

    public static void test(int n){
        int a = 1;
        int b = 1;
        while (b < n || a < n){
            System.out.print(a + " " +  b + " ");
            a = a + b;
            b = a + b;
        }
    }

    public static int fib(int num) {
        //判断：是否是第一个数和第二个数
        if (num == 1 || num == 2) {
            return 1;
        } else {
            //循环调用本函数
            return fib(num - 2) + fib(num - 1);
        }
    }
}
