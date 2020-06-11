package algorithm.sort;

/**
 * Created by suneee on 2018/11/13.
 * 选择排序
 */
public class SelectSort {

    public static void selectSort(int[] arr) {
        int len = arr.length;
        if(len < 0) return;

        for (int i = 0; i < len;i++){
            int value = arr[i];
            int temp = arr[i];
            int k = i;
            for(int j=i+1;j<len;j++){
                if(temp>arr[j]){
                    temp = arr[j];
                    k = j;
                }
            }
            arr[i] = temp;
            arr[k] = value;
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        selectSort(new int[]{4,5,6,1,2,3});
        System.out.println();
        //selectSort(new int[]{4,3,6,7});
       // System.out.println();
        //selectSort(new int[]{6,5,4,3,2,1});
    }
}
