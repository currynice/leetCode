package com.cxy.brush.leetcode.editor.cn;

//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
//
//
//
// 示例 1：
//
//
//输入：x = 121
//输出：true
//
//
// 示例 2：
//
//
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3：
//
//
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 示例 4：
//
//
//输入：x = -101
//输出：false
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
//
//
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学
// 👍 1463 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution9 {

    public boolean isPalindrome(int x) {
        // 负数不是回文
        if(x<0){
            return false;
        }

        //翻转后比较和原始数字是否相等，翻转参考第七道题目[整数反转]的两个方法
        int original = x;

        //solution1 start -----------------------------------
//        long reverse = 0;
//
//        while(x!=0){
//            reverse = reverse*10 +x%10;
//            x /= 10;
//        }
//       return original == (int)reverse;
        //solution1 end -----------------------------------

        //solution2 start -----------------------------------

        int reverse = 0;

        while(x!=0){

            //拿到最后一位
            int lastNum = x%10;

            if(reverse > 214748364 || (reverse==214748364 && lastNum>7)){
                reverse=0;
                break;
            }
            //不用考虑负数的情况

            reverse = reverse*10 + lastNum;

            //消除最后一位
            x /= 10;
        }
        return original==reverse;

        //solution2 end -----------------------------------

    }





        public boolean isPalindrome2(int x) {
        //负数一定不是回文数
        if (x < 0) {
            return false;
        }

        //比较翻转后的数字和原数字是否相等
        long curr = 0;
        //保留下输入
        int num = x;

        while (num != 0) {
            curr = curr * 10 + num % 10;
            num /= 10;
        }

        return curr == x;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
