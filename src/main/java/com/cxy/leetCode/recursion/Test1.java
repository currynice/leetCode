package com.cxy.leetCode.recursion;


import java.util.HashMap;
import java.util.Map;

public class Test1 {


    public static void main(String args[]){
          System.out.println(f2(10));
        System.out.println();
    }
    private static Map<Integer,Integer> resultMap = new HashMap<>();

    //根据递推公式 f(n) = f(n-1) + f(n-2) , 终止条件f(1) = 1,f(2) = 2  设计递归代码
    public static  int f(int n) {
            if (n == 1) return 1;
            if (n == 2) return 2;
            int result =  f(n-1) + f(n-2);
            resultMap.put(n,result);
            return result;
    }


    //非递归代码
    public static  int f2(int n) {

            if (n == 1) return 1;

            if (n == 2) return 2;

            int ret = 0;

            //即f(n-1)，初始值f(2)
            int pre = 2;

            //即f(n-2),初始值f(1)
            int prepre = 1;

            for (int i = 3; i <= n; ++i) {

                ret = pre + prepre;

                prepre = pre;

                pre = ret;

            }

            return ret;

        }
    }

