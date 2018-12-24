package algorithm.search;

/**
 * Created by suneee on 2018/12/21.
 * 二分法查找: 要求必须数组,而且需要排序完成
 */
public class BsSearch {

    public static void main(String[] args) {
        //System.out.println(bsSearch(new int[]{1, 2, 3, 4, 5}, 5, 2));
        System.out.println(searchFirstElement(new int[]{1, 2, 2, 2, 2, 2, 4, 5}, 5, 2));
        System.out.println(searchLastElement(new int[]{1, 2, 2, 2, 2, 3, 5}, 5, 2));
        System.out.println(searchFirstGEElement(new int[]{1, 2, 4, 4, 5}, 5, 3));
        System.out.println(searchLastLTElement(new int[]{1, 2, 4, 4, 5}, 5, 3));
    }

    /**
     * 二分法查询
     *
     * @param array 数组
     * @param n     数组个数
     * @param value 查询值
     * @return
     */
    public static int bsSearch(int[] array, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) { // 注意要row<=high,防止死循环
            // int mid = (low + high) / 2; // 容易溢出
            // int mid = low + (high - low) / 2; // 常规运算
            int mid = low + ((high - low) >> 1);
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                low = mid + 1; // 注意要+1;
            } else {
                high = mid - 1;// 注意要-1;
            }
        }
        return -1;
    }

    /**
     * 查询重复元素第一个
     *
     * @param array
     * @param n
     * @param value
     * @return
     */
    public static int searchFirstElement(int[] array, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] == value) {
                if ((mid == 0) || (array[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (array[mid] < value) {
                low = mid + 1; // 注意要+1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查询重复元素最后一个
     *
     * @param array
     * @param n
     * @param value
     * @return
     */
    public static int searchLastElement(int[] array, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] == value) {
                if ((mid == n - 1) || (array[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (array[mid] < value) {
                low = mid + 1; // 注意要+1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查询第一个大于等于给定元素的元素
     *
     * @param array
     * @param n
     * @param value
     * @return
     */
    public static int searchFirstGEElement(int[] array, int n, int value) {
        int low = 0;
        int high = n - 1;

        int i = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] >= value) {
                if((mid == 0) || (array[mid-1] < value)){
                    return mid;
                }else{
                    high = mid - 1;
                }
            } else if (array[mid] < value) {
                low = mid + 1; // 注意要+1;
            }
        }
        return array[i + 1];
    }

    /**
     * 查询最后一个小于等于给定元素的元素
     *
     * @param array
     * @param n
     * @param value
     * @return
     */
    public static int searchLastLTElement(int[] array, int n, int value) {
        int low = 0;
        int high = n - 1;

        int i = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] <= value) {
                if((mid == n-1) || (array[mid+1] > value)){
                    return mid;
                } else{
                    low = mid + 1;
                }
            }else if (array[mid] > value){
                high = mid - 1;
            }
        }
        return array[i - 1];
    }
}
