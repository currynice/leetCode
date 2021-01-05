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

        //锁两个数
        //第一个锁住的数，从下标0开始遍历，后面至少需要三个数，所以，从 0- （length-4） 即a<nums.length-3
            for(int a=0;a<nums.length-3;a++) {
            //排重
            if (a != 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            //第二个锁住的数，从下标a+1开始遍历，后面至少需要2个数，所以，从 0- （length-3）即 b<nums.length-2
            for(int b=a+1;b<nums.length-2;b++) {
                //排重
                if (b != a+1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                //左右指针在b,length-1之间尝试对撞
                int left = b+1, right = nums.length - 1;

                while (left < right) {
                    int sum = nums[a] + nums[b]+ nums[left] + nums[right];
                    if (sum<target) {
                        //子数组提供的元素和(left+right)小了,left右移
                        left++;
                    } else if (sum>target) {
                        //子数组提供的元素和(left+right)大了,right左移
                        right--;
                    } else {
                        //find a solve
                        result.add(Arrays.asList(nums[a],nums[b],nums[left],nums[right]));
                        //这里也要去重
                        //因为已经匹配上了，所以如果 left右边 还是一样的值，那么肯定会重
                        while (left<right && nums[left] == nums[left+1]) left++;
                        //因为已经匹配上了，所以如果right左边 还是一样的值，那么肯定会重
                        while (left<right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }

            }
        }

        return result;
    }


//    public static void main(String[] args) {
//        int[] nums = new int[]{0,0,0,0};
//        List<List<Integer>> result = fourSum(nums,0);
//        System.out.println(Arrays.toString(result.toArray()));
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
