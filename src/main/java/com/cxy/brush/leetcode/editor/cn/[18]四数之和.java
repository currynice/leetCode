package com.cxy.brush.leetcode.editor.cn;
//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 707 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution18 {

    public  List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums.length<4){
            return result;
        }

        Arrays.sort(nums);

        //lock (a 后面至少三个数，a+3<nums.length)
        for(int a=0;a<nums.length-3;a++){
            //去重
            if(a>0 && nums[a]==nums[a-1]){
                continue;
            }

            //lock(b,b后面至少两个数,b+2<nums.length)
            for(int b=a+1;b<nums.length-2;b++){
                //去重
                if(b>a+1 && nums[b]==nums[b-1]){
                    continue;
                }

                //剩余两个双指针：left ,right
                int left = b+1;
                int right = nums.length-1;

                while(left<right){
                    int total = nums[a]+nums[b]+nums[left]+nums[right];
                    if(total<target){
                        //left+right 小了
                        left++;
                    }else if (total>target){
                        //left+right 大了
                        right--;
                    }else {
                        result.add(Arrays.asList(nums[a],nums[b],nums[left],nums[right]));
                        //去重
                        while(left<right && nums[left]==nums[left+1]){
                            left++;
                        }
                        while (left<right &&  nums[right]==nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }

            }

        }


        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
