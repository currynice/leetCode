package com.cxy.brush.leetcode.editor.cn;//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找 
// 👍 567 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution50 {

    private double quickMul(double x,long N){
        //递归边界
        if(N==0){
            return 1.0;
        }
        //计算 y=N//2 ,定义//为 N/2的结果对下取整，
        // N//2 进行奇偶判断,奇数要多*x
        double y = quickMul(x,N/2);
        return N%2==0? y*y : y*y*x;

    }

    public double myPow(double x, int n) {
        //因为int范围 [-2^31,2^31-1] , -N 不能简单取绝对值，用long long代替稳妥些
        long N = n;

        if(N>=0){
            return quickMul(x,N);
        }else {
            return 1.0/quickMul(x,-N);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
