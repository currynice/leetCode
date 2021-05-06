package com.cxy.brush.leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3511 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {


//    public String longestPalindrome(String s) {
//        //dp[i][j] 记录 s.substring(i,j) 对应的字符串是否是回文字符串(dp[i][j]==1)
//        // s="abac", 那么dp[0][2]==1 , dp[0][3]==0
//        int dp[][] = new int[s.length()][s.length()];
//
//        if(s.length()==1 ){
//            return s;
//        }
//
//        //预处理:所有单字符字符串肯定是回文字符串
//        for(int a=0;a<s.length();a++){
//            dp[a][a] = 1;
//        }
//
//        int length = s.length();
//        //记录最长回文的左下表
//        int maxStartIndex = 0;
//        //记录最长回文的右下表
//        int maxEndIndex = 0;
//        //最长回文的长度
//        int maxLength = 1;
//
//
//
//        //遍历所有长度的字符子串
//        for (int j = 1; j < length; j++) {
//            for (int i = 0; i < j; i++) {
//                /*
//                    若当前子串：
//                        1、第一个字符 和 最后一个字符 相等
//                        2、长度<3(长度为1显然是回文,长度为2只要i,j相等也是回文) 减去首尾俩字符的子串也满足回文特性
//                */
//                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]==1)) {
//                    dp[i][j] = 1;
//                    if (j - i + 1 > maxLength) {
//                        maxLength = j - i + 1;
//                        maxStartIndex = i;
//                        maxEndIndex = j;
//                    }
//                }
//
//            }
//        }
//
//        return s.substring(maxStartIndex, maxEndIndex + 1);
//    }


    public String longestPalindrome(String s) {
        if(s.length()==1){
            return s;
        }
        int dp[][] = new int[s.length()][s.length()];
        //单字符的字符串都是回文
        for(int a=0;a<s.length();a++){
            dp[a][a] = 1;
        }

        int length = s.length();

        //遍历过程中，只更新结果回文的两个指针,初始为第一个字符
        int resultStart = 0;
        int resultEnd = 0;

        int maxLen = 1;//更新依据

        //假设length=5,
        //j=1; i=0
        //j=2; i=0,i=1
        //j=3; i=0,i=1,i=2
        //j=4; i=0,i=1,i=2,i=3
        //更新dp[i][j]
        for(int j=1;j<length;j++){
            for(int i=0;i<j;i++){
                //对于 [i,j] 左闭右闭 的字符串是否是回文，条件如下：
                // 1）s.charAt(i)==s.charAt(j)
                // .... 开始考虑减去首尾后的子串是否是回文
                // ....  不得不考虑 corner case:
                //    如果[i,j] 长度是1或2，就不必判断 [i+1,j-1] 了
                // 2) 长度<3(长度为1显然是回文,长度为2只要满足条件1也是回文)
                // 3) 只有长度>=3 ，减去首尾俩字符的子串也满足回文特性
                boolean baseCase = s.charAt(i)==s.charAt(j);

                if(baseCase){
                   if(j-i+1<3){
                       dp[i][j] =1;
                   }else {
                       if(dp[i+1][j-1]==1){
                           dp[i][j] =1;
                       }
                   }
                }

               if(dp[i][j]==1){
                   //更新result
                   if (j - i + 1 > maxLen) {
                       maxLen = j - i + 1;
                       resultStart = i;
                       resultEnd = j;
                   }
               }

            }
        }

        return s.substring(resultStart,resultEnd+1);


    }

//    public static void main(String[] args) {
//        Solution5 s= new Solution5();
////        System.out.println(s.longestPalindrome("a"));
////        System.out.println(s.longestPalindrome("ac"));
//        System.out.println(s.longestPalindrome("cbbd"));
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
