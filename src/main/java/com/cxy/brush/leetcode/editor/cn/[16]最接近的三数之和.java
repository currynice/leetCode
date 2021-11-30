package com.cxy.brush.leetcode.editor.cn;
//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。
//
//
//
// 示例：
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics 数组 双指针 排序
// 👍 829 👎 0



//leetcode submit region begin(Prohibit modification and deletion)
import java.util.Arrays;

class Solution16 {


    /**
     * 首先进行数组排序，时间复杂度 O(nlogn)
     * 在数组 nums 中，进行遍历
     * 使用前指针指向 start = i + 1 处，后指针指向 end = nums.length - 1 处(结尾处)
     *     sum = nums[i] + nums[start] + nums[end] 的结果，判断 sum 与目标 target 的距离，如果 target 和 当前result的距离，  则更新 result = sum
     *
     * 因为数组有序，如果 sum > target 则 end--，如果 sum < target 则 start++，如果 sum == target 直接返回结果(题意仅唯一答案)
     * 整个遍历过程，固定值为 n 次，双指针为 n 次，时间复杂度为 O(n^2)
     * 总时间复杂度：O(nlogn) + O(n^2) = O(n^2)
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = nums[0]+nums[1]+nums[2];

        for(int i=0;i<nums.length;i++){
            int left = i + 1, right = nums.length - 1;
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // sum 比 当前result 更接近 target
                if(Math.abs(target - sum) < Math.abs(target - result)){
                    result = sum;
                }
                if (sum > target)
                    right--;
                else if (sum < target)
                    left++;
                else
                    return result;
                }
            }
            return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2};
        Solution16 s = new Solution16();
        System.out.println(s.threeSumClosest(nums,3));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
