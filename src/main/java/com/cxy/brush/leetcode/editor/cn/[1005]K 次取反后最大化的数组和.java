package com.cxy.brush.leetcode.editor.cn;
//给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
//
//
// 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
//
//
// 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
//
// 以这种方式修改数组后，返回数组 可能的最大和 。
//
//
//
// 示例 1：
//
//
//输入：nums = [4,2,3], k = 1
//输出：5
//解释：选择下标 1 ，nums 变为 [4,-2,3] 。
//
//
// 示例 2：
//
//
//输入：nums = [3,-1,0,2], k = 3
//输出：6
//解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
//
//
// 示例 3：
//
//
//输入：nums = [2,-3,-1,5,-4], k = 2
//输出：13
//解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 104
// -100 <= nums[i] <= 100
// 1 <= k <= 104
//
// Related Topics 贪心 数组 排序
// 👍 127 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {


//        - 如果数组中存在 0，那剩余次数都用于翻转0；
//
//        - 如果数组中不存在 0，且剩余次数是偶数，得以在不减小数组的和的前提下，用完修改次数；
//
//        - 如果数组中不存在 0，且剩余次数是奇数，那必须使用一次修改将最小的正数变为负数。

        //因为nums[i] 有范围，因此使用容量 200的map保存每个数出现的频次，避免排序： -100~100 ， key:数字  value: 出现次数
        Map<Integer,Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        //1.优先修改负数   （但可能导致出现了更小的正数，及时更新）
        for(int i=-100;i<0;i++){
            if(freq.containsKey(i)&&k > 0) {
                int num = freq.get(i);
                int fix = Math.min(k,num);//修改fix次
                k-=fix;
                int preFreq_ = freq.getOrDefault(-i, 0);
                freq.put(-i, preFreq_ + fix);
                freq.put(i, num-fix);
                }
        }

        for(int i=-100;i<0;i++) {
            if (freq.get(i) != null) {
                result += (i)*freq.get(i);
            }
        }

        //如果此时存在 0，或不存在 0，但剩余次数是偶数，那总和不会因翻转减少；
        if(k>0&&(freq.containsKey(0) || k%2==0)){
            k=0;
        }



        for(int i=1;i<101;i++){
            //如果数组中不存在 0，且剩余次数是奇数，那必须使用一次修改将最小的正数变为负数。
            if(k>0 && freq.get(i)!=null){

                    result +=(i)*(freq.get(i)-2);

                k=0;
            }else if(freq.get(i)!=null){
                result += (i)*freq.get(i);
            }
        }


        return result;


    }

    public static void main(String[] args) {
        System.out.println(new Solution1005().largestSumAfterKNegations(new int[]{-2,5,0,2,-2},3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
