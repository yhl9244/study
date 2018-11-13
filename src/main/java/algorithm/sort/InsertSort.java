package algorithm.sort;

/**
 * Created by suneee on 2018/11/12.
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        int len = arr.length;
        if(len < 0) return;

        for (int i = 1; i < len; i++) {
            int value = arr[i];
            int j = i - 1;
            for(;j>=0;j--){
                if(arr[j] > value){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = value;
        }
        for (int datum : arr) {
            System.out.print(datum+" ");
        }
    }

    public static void main(String[] args) {
        //insertSort(new int[] {4,5,6,1,2,3});
        //System.out.println();
        insertSort(new int[] {5,3,6,2});
    }
}
