package com.cxy.brush.leetcode.editor.cn;//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 954 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//考虑当前位置是一个负数.那么最好以它前一个位置结尾的数组中某段的积也是个很小的负数，这样负负得正，
//如果当前位置是一个正数，那么最好以它前一个位置结尾的某个段的积是个很大正数
// 于是这里我们可以再维护一个 fmin(i)，表示以第 i 个元素结尾的乘积最小（负数绝对值最大）子数组的乘积，
// dp方程：

//        fmax(i) = MAX_(fmax(i-1)*ai,fmin(i-1)*ai,ai)
//        fmin(i) = MIN_(fmax(i-1)*ai,fmin(i-1)*ai,ai)
//​
//     三者取大，以及三者取小
//

class Solution {
    public int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
