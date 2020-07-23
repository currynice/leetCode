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

    public static void main(String[] args) {
        System.out.println(fib(20));
        System.out.println(fibWithDict(20));
    }

}
