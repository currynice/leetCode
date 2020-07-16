package com.cxy.brush.leetcode.editor.cn;
//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组 
// 👍 499 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution66 {
    public int[] plusOne(int[] digits) {

        //一般执行一次就return了, 如果执行多次，正好说明由于进位+1
        for(int i = digits.length-1;i>=0;i--){
            digits[i]++;
            digits[i] %= 10;
            //不需要进位
            if(digits[i]!=0){
                return digits;
            }
        }
        if(digits[0]==0){
            //数组扩容,正好除了第一个元素外，其余元素都被初始化为0
            digits = new int[digits.length+1];
            digits[0] = 1;
            return digits;
        }else {
            return digits;
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
