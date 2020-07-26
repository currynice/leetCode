package com.cxy.brush.leetcode.editor.cn;//
// 给定不同面额的硬币 coins 和一个总金额 amount。
// 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 
//
// 示例 1: 
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3 
//解释: 11 = 5 + 5 + 1 
//
// 示例 2: 
//
// 输入: coins = [2], amount = 3
//输出: -1 
//
// 
//
// 说明: 
//你可以认为每种硬币的数量是无限的。 
// Related Topics 动态规划 
// 👍 717 👎 0


import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    Map<Integer,Integer> map = new HashMap<>();
    /**
     * 状态- 原问题和子问题中变化的变量，即目标金额
     * dp: 对于目标金额n,至少需要dp(n) 个硬币
     * 选择-对于每个状态，如何选择来改变当前状态，再从面额列表里选一个硬币，目标金额将减少，所以选择需要硬币最少的结果
     *
     *
     * dp(n) = 0  n=0
     *         -1, n<0
     *         min{dp(n-coin)+1)}
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

      return   dp(amount,coins);
    }


    private int dp(int n,int[] coins){
//        if(map.get(n)!= null){
//            return map.get(n);
//        }

        if(n==0){
            return 0;
        }
        if(n<0){
            return -1;
        }
        //求最小值，结果初始化为正无穷
        int result  = Integer.MAX_VALUE;
        for(int i =0;i<coins.length;i++ ){
            int sub = dp(n-coins[i],coins);
            if(sub==-1){
                continue;
            }
            result = Math.min(result,1+sub);
        }
        return result!=Integer.MAX_VALUE?result:-1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
