package com.cxy.brush.leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2373 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        //todo 自己实现快排
        Arrays.sort(nums);
        for(int a=0;a<nums.length;a++) {
            if(a!=0 && nums[a]==nums[a-1]){
                continue;
            }
            //left不设置为1? 避免重复解
            int left = a+1, right = nums.length - 1;
            //left肯定小于right,等于时子数组只能提供一个元素，不满足条件
            while (left < right) {
                int sum = nums[a] + nums[left] + nums[right];
                if (sum<0) {
                    //子数组提供的元素和(left+right)小了,left右移
                    left++;
                } else if (sum>0) {
                    //子数组提供的元素和(left+right)大了,right左移
                    right--;
                } else {
                  //find a solve
                        result.add(Arrays.asList(nums[a],nums[left],nums[right]));
                        //这里也要去重
                    //因为已经匹配上了，所以如果 left右边 还是一样的值，那么肯定会重了
                    while (left<right && nums[left] == nums[left+1]) left++;
                    //因为已经匹配上了，所以如果right左边 还是一样的值，那么肯定会重了
                    while (left<right && nums[right] == nums[right-1]) right--;

                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 0, 2, 2};
        List<List<Integer>> result  =  threeSum(nums);
        System.out.println();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
