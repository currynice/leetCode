package com.cxy.brush.leetcode.editor.cn;

//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 
// 👍 1110 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution6 {


    /**
     * 遍历每个字符c ，整体上看，c所在的行是 1,2,..,n,（方向反转）...,2,1
     * 因此，模拟 当前字符行索引，遍历s时把每个字符c填到正确的行 res[i]
     * @param s
     * @param numRows
     * @return
     */
    public  String convert(String s, int numRows) {
        if(numRows <2){
            return s;
        }

        //一行一行地存
        List<StringBuilder> rows = new ArrayList<>();;
        for(int a=0;a<numRows;a++){
            rows.add(new StringBuilder());
        }
        //第一个字符将落在第 0 行
        int i=0;
        int flag= -1;

        for(char c: s.toCharArray()){
            rows.get(i).append(c);

            //如果已经到了第 0 行 或者第 numRows-1 行，需要改变方向
            if(i==0 || i==numRows-1){
                flag = -flag;
            }

            // flag = 1 ,表示从上到下  (0，1，2，。。。numRows-1)
            // flag = -1 ,表示从下到上 (numRows-1，。。。2，1，0)
            i += flag;
        }
        return rows.stream().map(StringBuilder::toString).collect(Collectors.joining());

    }




    public static void main(String[] args) {
        String s = "AB";
        System.out.println(new Solution6().convert(s,1));
    }



    private void test() {
        int total = 19;

        //输出20次:  0,1,2,3....9,10 ;到达10以后:9,8,7,6,5,4,3,2,1

        int dest = -1;
        //已经打印的数字
        int now = 0;
        int curr = 0;
        while (now < 21) {

            System.out.println(curr);
            if (curr == 0 || curr == 10) {
                dest = -dest;
            }
            //递增 or 递减 1
            curr += dest;
            now++;
        }
    }



}
//leetcode submit region end(Prohibit modification and deletion)
