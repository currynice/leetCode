package com.cxy.brush.leetcode.editor.cn;
//给你一个整数数组 nums 和一个整数 k。
//
// 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。 
//
// 请返回这个数组中「优美子数组」的数目。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,1,2,1,1], k = 3
//输出：2
//解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
// 
//
// 示例 2： 
//
// 输入：nums = [2,4,6], k = 1
//输出：0
//解释：数列中不包含任何奇数，所以不存在优美子数组。
// 
//
// 示例 3： 
//
// 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 50000 
// 1 <= nums[i] <= 10^5 
// 1 <= k <= nums.length 
// 
// Related Topics 数组 哈希表 数学 滑动窗口 
// 👍 184 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *
     * 前缀和数组：快速计算数组某个区间的和
     * 0 <= j <= i ，[j,...,i] 数组中奇数个数是k ,[j,...,i] 数组的个数
     * 考虑 nums种符合条件的
     * 定义 pre[i] 为 [0,i] 中奇数的个数
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {

        int n  = nums.length;

        for(int a=0;a < n;a++){
            nums[a] %= 2; //转为 0,1
        }

//        pre[each] = pre[each-1] + [nums[each]&1]
//
//        pre[i] - pre[j-1] 等于 k  --- [j,i] 子数组中奇数个数是k
//        pre[j-1] 等于 pre[i] - k
//
//       解法：两层循环遍历得到 pre[j] - pre[i] == k的个数，时间复杂度 O(n^2)


//      优化：使用缓存优化到O(n) ，使用cnt数组统计S中每个值出现的次数
//                                  key:前缀和  value:前缀和个数(左右区间不同的数组个数)，
//      只需遍历一次，计算出前缀和 pre[each]后，在res中累加上    前缀和为 pre[i] - k 的个数
//


        //构建前缀和数组,
        int[] pre = new int[n];
        pre[0] = nums[0] &1;

        //下标是前缀和（即奇数的个数），值是出现次数。
        int[] cnt = new int[n+1];
        //前缀和为0的，初始为 1个:  因为当 可以1个偶数都不取，所以不要忘了加1) 有错请指正（小白一个）
        cnt[0] = 1;
        cnt[pre[0]]++;

        int result = (pre[0] >= k)? cnt[pre[0] - k] :0;


        for (int i = 1; i < n; ++i) {
            pre[i] = pre[i-1] + (nums[i] & 1);


            cnt[pre[i]]++;

            //找 当前 i 之前有多少个 j满足
            if(pre[i] >= k){
                //当前前缀和 大于等于k，说明前面有符合条件的结果， result 中累加上与当前前缀和差值为 k 的前缀和的个数。
                result = result + cnt[pre[i] - k];
             }
        }

        return result;


    }



    public static void main(String[] args) {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        new Solution().numberOfSubarrays(nums,2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
