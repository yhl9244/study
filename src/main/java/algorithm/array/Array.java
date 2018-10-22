package algorithm.array;

/**
 * Created by suneee on 2018/10/11.
 */
public class Array {

    /**
     * 1. 数组的插入,删除,按照下标随机访问操作
     * 2. 数组数据为int类型
     */
    //定义整型数据data保存数据
    public int data[];
    //定义数组长度
    private int n;
    //定义中保存的数据个数
    private int count;

    //构造方法，定义数组大小
    public Array(int capacity){
        this.data = new int[]{0,1,2,3,4};
        this.n = capacity;
        this.count=capacity;
    }

    // 根据索引,找到元素返回
    public int find(int index){
        if(index< 0 || index >= count || index >=data.length) return -1;
        return data[index];
    }

    // 根据索引删除元素
    public boolean delete(int index) {
        if(index<0 || index >= count || index >=data.length) return false;
        //从删除位置开始，将后面的元素向前移动一位
        for (int i=index+1;i<count;i++){
            data[i-1] = data[i];
        }
        // 删除最后一个元素
        /*int[] newarray = new int[count-1];
        for(int i=0;i<count-1;i++){
            newarray[i] = data[i];
        }
        this.data = newarray;*/

        --count;
        return true;
    }

    // 向数组插入元素
    public boolean insert(int index, int value){
        if(index<0 || index >=count)return false;
        //if(count == n) return false;
        // 数组长度+1
        int[] newarray = new int[count+1];
        for(int i=0; i<data.length;i++){
            newarray[i] = data[i];
        }
        data = newarray;
        for (int i : data){
            System.out.print(i+ " ");
        }
        System.out.println("------------");
        for (int i= count-1;i >=index; i--){
            data[i+1] = data[i];
        }
        data[index] = value;
        ++count;
        return true;
    }

    public boolean insertToTail(int value) {
//        if (count == n) return false;不是太懂
        //数组长度增加1
        int[] arr = new int[count+1];
        for (int i = 0; i < data.length; i++) {
            arr[i] = data[i];
        }
        data=arr;
        data[count++] = value;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(6);
        /*System.out.println(array.find(5));
        System.out.println(array.find(3));
        array.delete(5);
        for (int i : array.data){
            System.out.print(i+" ");
        }
        System.out.println("----------------");
        array.delete(2);
        for (int i : array.data){
            System.out.print(i+ " ");
        }*/
        array.insert(3, 7);
        for (int i : array.data){
            System.out.print(i+ " ");
        }
    }
}
