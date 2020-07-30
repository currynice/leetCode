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



    /**
     * 在dp数组（一张表）上自底向上，完成最终结果的推算。
     * @param
     * @param coins
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dbtable = new int[amount+1]; // dbtable[i] = x : 当⽬标⾦额为 i 时，⾄少需要 x 枚硬币。
        // init db table ,初始化值为 amount+1( 因为凑 amount ⾦额，硬币数最多等于 amount （全是1元硬币），amount + 1 在本例相当于正⽆穷)
        for(int index=0;index<dbtable.length;index++){
            dbtable[index] = amount+1;
        }

        //base case
        dbtable[0] = 0;

        for(int i=0;i<dbtable.length;i++){
            for(int coin:coins) {
                if (i < coin) {//当前金币小于目标金额
                    continue;
                }
                dbtable[i] = Math.min(dbtable[i], dbtable[i - coin] + 1); //举个例子： 目标金额为7,coin 为2， 如果 7 -2 = 5 ，即 目标金额为5的解已经有了，那么目标金额为7的解就是目标金额为5的解+1
            }
        }

        return  dbtable[amount]==amount+1?-1: dbtable[amount];


    }


    //使用缓存，避免重复计算
    Map<Integer,Integer> map = new HashMap<>();
    /**
     * 状态- 原问题和子问题中变化的变量，即目标金额
     * dp: 目标金额n,由至少需要dp(n) 个硬币组成
     * 选择-对于每个状态，如何选择来改变当前状态，再从面额列表里选一个硬币，目标金额将减少，选需要硬币最少的情况
     *
     *
     * dp(n) = 0  n=0   base case
     *         -1, n<0  base case
     *         min{dp(n-coin)+1)}
     * @param coins
     * @param amount
     * @return
     */
//    public int coinChange2(int[] coins, int amount) {
//
//        return   dp(amount,coins);
//    }

    /**
     * 递归
     * @param n
     * @param coins
     * @return
     */
//    private int dp(int n,int[] coins){
//        if(map.get(n)!= null){
//            return map.get(n);
//        }
//
//        if(n==0){
//            return 0;
//        }
//        if(n<0){
//            return -1;
//        }
//        //硬币的个数，结果初始化为正无穷
//        int result  = Integer.MAX_VALUE;
//        for(int i =0;i<coins.length;i++ ){
//            //当前目标金额 n-coins[i]
//            int sub = dp(n-coins[i],coins);
//            if(sub==-1){
//                continue;
//            }
//            //比较当前结果和 子问题+1个硬币谁小
//            result = Math.min(result,1+sub);
//        }
//        return result!=Integer.MAX_VALUE?result:-1;
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
