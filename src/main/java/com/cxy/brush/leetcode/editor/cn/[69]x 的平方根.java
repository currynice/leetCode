package com.cxy.brush.leetcode.editor.cn;//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 570 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution69 {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        // 针对特殊测试用例(超过Integer.MAX_VALUE)
        long left = 1;
        long right = x / 2;
        while (left < right) {
            // 模板，取右中位数，如果取左中位数，代码会进入死循环  https://www.liwei.party/2019/06/17/leetcode-solution-new/search-insert-position/
             long mid = left + (right - left + 1) / 2;
         //   long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;
    }


    public static double accurateSqrt(int a ,double precision) {
        if (a == 0) {
            return 0;
        }
        float x = a;
        while (x * x > a) {
            double pre = x;
            x = (x + a / x) / 2;//next
            if(Math.abs(pre-x)<precision){
                return x;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(accurateSqrt(5,1e-6));

        System.out.println(  Math.sqrt(5));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
