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
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {


    public static void main(String[] args) {
        int[] nums = new int[]{-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));
    }


    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();


        if (nums == null || nums.length < 3) return result;


        //排序
        Arrays.sort(nums);

        for (int a = 0; a < nums.length; a++) {

            //当前最小值都大于0，无解
            if(nums[a]>0){
                break;
            }

            //去重，举例 [-9,1,1,3,2,-6] , a=1:正常执行，a=2: 可以跳过
            if (a > 0 && nums[a] == nums[a-1]) {
                continue;
            }

            int left = a+1;  //待与nums[a] 成组
            int right = nums.length -1; //待与nums[a] 成组

            //当 left==right ，意味着只能提供一个元素,pass
            while(left<right){
                int total = nums[a] + nums[left] + nums[right];

                if(total<0){
                    // left+ right 太小了
                    left++;
                }else if(total>0){
                    // left+ right 太大了
                    right--;

                }else {
                    //放入结果
                    result.add(Arrays.asList(nums[a],nums[left],nums[right]));

                    //去重，尽可能内缩 left,right的区间
                    while (left < right && nums[left]== nums[left+1]){
                        left++;
                    }

                    while(left < right && nums[right]== nums[right-1]){
                        right--;
                    }


                    left++;
                    right--;
                }


            }

        }

        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
