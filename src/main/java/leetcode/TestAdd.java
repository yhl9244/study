package leetcode;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class TestAdd {
//    public int[] twoSum(int[] nums, int target) {
//        int[]  result = new int[2];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = (i+1); j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    result[0] = i;
//                    result[1] = j;
//                    break;
//                }
//            }
//        }
//        return result;
//    }

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] {i, map.get(complement)};
                }
                map.put(nums[i], i);
            }
            return null;
        }

}
