package com.cxy.brush.leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8623 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(null==cache.get(target-nums[i])){
                cache.put(nums[i],i);
            }else {
                return new int[]{i,cache.get(target-nums[i])};
            }
        }
        return null;
    }

    /**
     * 双指针(前提: nums升序排列，本题不适用，sort会损失原数组的下标信息)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0,right = nums.length - 1;
        //一前一后
        while(left<right){
            int sum = nums[left] + nums[right];
            if(sum == target){
                return new int[]{left,right};
            }else if(sum > target){
                //大了,sum小一点
                right--;
            }else if(sum < target){
                //大了,sum 大一点
                left++;
            }
        }
        return new int[]{-1,-1};
    }

}
//leetcode submit region end(Prohibit modification and deletion)
