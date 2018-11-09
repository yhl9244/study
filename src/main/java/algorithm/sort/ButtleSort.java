package algorithm.sort;

/**
 * Created by suneee on 2018/11/8.
 */
public class ButtleSort {

    public static void buttleSort(int[] data) {

        int len = data.length;

        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = 0; j < len - 1 - i; j++){
                if(data[j] > data[j+1]){
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                System.out.println(i);
                break;
            }
        }

        for (int datum : data) {
            System.out.print(datum+" ");
        }
    }

    public static void main(String[] args) {
        buttleSort(new int[]{4,5,6,3,2,1});
        System.out.println();
        buttleSort(new int[]{3,5,4,1,2,6});
    }
}
