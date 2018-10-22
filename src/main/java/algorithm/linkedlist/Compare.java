package algorithm.linkedlist;

/**
 * Created by suneee on 2018/10/16.
 */
public class Compare {

    private static int[] array = new int[1000000];

    static {
        for (int i = 0; i<array.length; i++){
            array[i] = i;
        }
    }

    public static int test1(int data){
        if(array==null || array.length == 0){
            return -1;
        }
        int i = 0;
        while (i < array.length){
            if(array[i] == data){
                return array[i];
            }
            i++;
        }
        return -1;
    }

    public static int test2(int data){
        if(array==null || array.length == 0){
            return -1;
        }
        if(data == array[array.length-1]){
            return array.length-1;
        }
        // 创建临时变量保存
        int temp = array[array.length-1];
        array[array.length-1] = data;
        int i = 0 ;
        while (array[i] == data){
            i++;
        }
        // 恢复数组变量
        array[array.length-1] = temp;
        if(i == array.length-1){
            return -1;
        }else {
            return i;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(test1(999999));
        long end = System.currentTimeMillis();
        System.out.println("test1 执行时间:" + (end-start));
        start = System.currentTimeMillis();
        System.out.println(test2(999999));
        end = System.currentTimeMillis();
        System.out.println("test2 执行时间:" + (end-start));
    }
}
