package com.cxy.brush.leetcode.editor.cn;

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
// Related Topics 数学
// 👍 2699 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution7 {
    public int reverse(int x) {
        if(x>-10 && x<10){
            return x;
        }

        int result = 0;
        // 1234/10
        while(x!=0){
            int lastNum = x%10;//拿到末位数字
            // -214748364,8 , 214748364,7
            //如果拼接后结果可能不满足32位有符号整数

            // 1.当前部分加上一位后已经小于 Int32最小值的前9位
            // 2.当前部分 等于Int32最小值，但最后一位小于 -8
            if(result<-214748364 ||(result==-214748364 && lastNum<-8)){
                return 0;
            }

            //当前部分 已经大于 Int32最大值的前9位
            // 或当前部分 等于Int32最大值，但最后一位大于7
            if(result>214748364 ||(result==214748364 && lastNum>7)){
                return 0;
            }

            result = result*10 + lastNum;
            //排除末位数字
            x /=10;
        }

        return result;

    }


    public static void main(String[] args) {
        // 取 number 的末尾数字
//        System.out.println(reverse(1234));
//        System.out.println(Integer.MAX_VALUE); //2147483647
//        System.out.println(Integer.MIN_VALUE); //-2147483648

        // 2147483651，翻转后溢出
//        System.out.println(reverse(1563847412));


    }
}
//leetcode submit region end(Prohibit modification and deletion)
