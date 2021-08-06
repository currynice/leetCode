package com.cxy.brush.leetcode.editor.cn;
//请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
//
// 函数 myAtoi(string s) 的算法如下： 
//
// 
// 读入字符串并丢弃无用的前导空格 
// 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。 
// 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。 
// 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 
//2 开始）。 
// 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固
//定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
// 返回整数作为最终结果。 
// 
//
// 注意： 
//
// 
// 本题中的空白字符只包括空格字符 ' ' 。 
// 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "42"
//输出：42
//解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
//第 1 步："42"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："42"（读入 "42"）
//           ^
//解析得到整数 42 。
//由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。 
//
// 示例 2： 
//
// 
//输入：s = "   -42"
//输出：-42
//解释：
//第 1 步："   -42"（读入前导空格，但忽视掉）
//            ^
//第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
//             ^
//第 3 步："   -42"（读入 "42"）
//               ^
//解析得到整数 -42 。
//由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
// 
//
// 示例 3： 
//
// 
//输入：s = "4193 with words"
//输出：4193
//解释：
//第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
//             ^
//解析得到整数 4193 。
//由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
// 
//
// 示例 4： 
//
// 
//输入：s = "words and 987"
//输出：0
//解释：
//第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
//         ^
//解析得到整数 0 ，因为没有读入任何数字。
//由于 0 在范围 [-231, 231 - 1] 内，最终结果为 0 。 
//
// 示例 5： 
//
// 
//输入：s = "-91283472332"
//输出：-2147483648
//解释：
//第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
//          ^
//第 3 步："-91283472332"（读入 "91283472332"）
//                     ^
//解析得到整数 -91283472332 。
//由于 -91283472332 小于范围 [-231, 231 - 1] 的下界，最终结果被截断为 -231 = -2147483648 。 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 200 
// s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成 
// 
// Related Topics 数学 字符串 
// 👍 1083 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;

/**
 * 字符如何隐式转换为数字：
 *     一个char类型字符减去 '0' 隐式转换为数字
 *     一个数字加上'0' 隐式的转换为字符
 *
 *     System.out.println('2'-'0');
 *     System.out.println('z'-'0');
 *     System.out.println((char)(3 + '0'));
 *
 *
 */
class Solution8 {


    public  int myAtoi(String s) {
        if(s==null || s.trim().equals("")){
            return 0;
        }

        //默认正数
        boolean flag = true;

        int result = 0;

        int pointer = 0;

        //step1: 排除前导空格
        while(s.charAt(pointer)==' '){
            pointer++;
        }

        //step2: 只承认排除前导空格后的正负号（如果存在的话）
        if(s.charAt(pointer)=='+' || s.charAt(pointer)=='-'){
            flag = (s.charAt(pointer) == '+');
            pointer++;
        }

        System.out.println(Arrays.toString(s.substring(pointer).toCharArray()));

        for(char c: s.substring(pointer).toCharArray()){

            if(isDigit(c)){
                //如果会溢出，直接返回，不再解析； 如果不会溢出，te是null
                Integer te = isOver(flag?result:-result,c);
                if(te !=null){
                    return te;
                }
                result = result*10 + c-'0';
            }else {
                // 读到非数字，跳出循环，如果之前有数字(result非0)，可以正常返回
                // 或者就是第一次迭代就跳出循环了
                break;
            }
        }

        return flag?result:-result;
    }


    public static void main(String[] args) {
        String s = "     -1111 aaaaaa22";
//        System.out.println(myAtoi(s));
    }



    /**
     * 判断c是否是数字型字符
     * ASCII 的数字字符范围是 48(0) --- 57(9)
     * @param c
     * @return
     */
    private static boolean isDigit(char c){
         return c>='0' && c<='9';
    }

    /**
     * 是否溢出 over，溢出截断
     * @param c
     * @return 如果c加到 res后发生溢出，返回 Integer.MIN_VALUE 或 Integer.MAX_VALUE,
     * 正常的话返回 null
     *
     */
    private static Integer isOver(int res,char c){
        // 1.当前部分加上一位后已经小于 Int32最小值的前9位
        // 2.当前部分 等于Int32最小值，但最后一位小于 -8
        if(res<-214748364 ||(res==-214748364 && -(c-'0')<-8)){
            return Integer.MIN_VALUE;
        }

        //当前部分 已经大于 Int32最大值的前9位
        // 或当前部分 等于Int32最大值，但最后一位大于7
        if(res>214748364 ||(res==214748364 && (c-'0')>7)){
            return Integer.MAX_VALUE;
        }

        //未溢出
        return null;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
