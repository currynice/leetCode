package com.cxy.taolu;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Description:
 *
 * 引入[缓存/备忘录]的递归时间复杂度：因为减少了不必要的节点计算，差不多是O（n）已经和迭代的动态规划差不多了。
 * 不过，这是「⾃顶向下」，动态规划叫做「⾃底向上」。
 * 「⾃顶向下」: 如一个 斐波那契 数列，从⼀个规模较⼤原问题 f(20) ，向下分解规模到 f(1) 和 f(2) ，逐层返回答案。
 * 「⾃底向上」：反着来，直接从最底下，问题规模最⼩的f(1) 和 f(2) 往上推，直到推到f(20) ，因此动态规划⼀般不需要递归，⽽是循环迭代
 *
 * 动态规划:自底向下  <br>
 * Date: 2020/7/23 17:47  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class DynamicPlanning {

    public static int fib(int N) {
        if (N == 1 || N == 2) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    public static int fibWithDict(int N){
            if (N < 1) return 0;
        //一个备忘录或缓存
         Map<Integer,Integer> dict = new HashMap<>();
// 初始化最简情况
            return helper1(dict, N);
        }

       private static int  helper1( Map<Integer,Integer> dict, int n) {
        // 终止条件
            if (n == 1 || n == 2) return 1;
        // 已经计算过
            if (dict.containsKey(n)) return dict.get(n) ;
            dict.put(n,helper1(dict, n - 1) + helper1(dict, n - 2));
            return dict.get(n);
        }

    /**
     * 在dp数组（一张表）上自底向上，完成最终结果的推算。
     * @param N
     * @return
     */
    public static int fibWithDynamic(int N){
       int[] dp = new int[N+1];//舍弃下标0
       dp[1] = 1;
       dp[2] = 1;

       for(int i= 3;i<N+1;i++){
           //fn = f(n-1) + f(n-2)
           dp[i] = dp[i-1] + dp[i-2];
       }
       return dp[N];
    }

    /**
     * 节省db table 所占内存的优化方法
     * @param n
     * @return
     */
    public static int fibWithDynamicO1(int n) {
        if (n == 2 || n == 1)
            return 1;
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }


    /**
     *
     * 「状态转移⽅程」听起来很⾼端。f(n) 即⼀个状态 n，是由状态 n - 1 和状态 n - 2 相加转移⽽来，即状态转移。
     *
     *  无论是 return f(n - 1) + f(n - 2)，还是 dp[i] = dp[i - 1] + dp[i - 2]，以及对备忘录 或 DP table 的初始化操作，
     *  都是围绕 状态转移⽅程 的不同形式。可⻅列出「状态转移⽅程」是解决问题的核⼼。
     *  状态转移⽅程直接代表着暴⼒解法。
     *
     * 如何优化状态转移方程下的暴力解?
     *   ⽆⾮是⽤备忘录(缓存) 或  DP table，对了，根据斐波那契数列
     * 的状态转移⽅程，当前状态仅和之前的两个状态有关，其实并不需要那么⻓
     * 的⼀个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就⾏
     * 了。所以，可以进⼀步优化，把空间复杂度降为 O(1)：
     * @param args
     */

    public static void main(String[] args) {
        System.out.println(fib(20));
        System.out.println(fibWithDict(20));
        System.out.println(fibWithDynamic(20));
    }

}
