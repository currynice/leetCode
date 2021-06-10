package com.cxy.brush.leetcode.editor.cn;

//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 字符串 动态规划 回溯算法 
// 👍 2084 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10 {
    /**
     * 定义指针 i,j 分别在s和p上从左到右移动，如果最后指针都能到末尾，返回true
     *
     * 因为*签名肯定有字符，所以只可能出现 p[j+1] =='*'
     *    1. s[i]==p[j]
     *       1.1 p[j] 匹配0次数，如s='aa',p='a*aa'
     *       1.2 p[j] 匹配多次（>=1），如s='aaa',p='a*'
     *
     *    2.s[i]!=p[j]
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchChar(char[] s, int i, char[] p, int j) {
        //当p已经探到底，但s不为空，匹配失败
        if(j >= p.length) {
            return i >= s.length;
        }
        //当前字符是否匹配
        //相等或p[j]是 '.'
        boolean case1 = i < s.length && ((s[i] == p[j]) || p[j] == '.');

        //p[j+1] =='*'
        if(j+1<p.length && p[j + 1] == '*') {
            //进行任意：
            //  如果*用来匹配多次（>=1），那么去掉ss的第一个字符，继续向后匹配。
            //  如果*用来匹配0次，那么ss保持不变，pp向后移动两位（忽略当前字符和下一位的'*'）。
            return isMatchChar(s, i, p, j + 2) || (case1 && isMatchChar(s, i + 1, p, j));
        }else{
            // 继续匹配后续的字符
            return case1 && isMatchChar(s, i + 1, p, j + 1);
        }
    }

    /**
     * 递归法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch0(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        return isMatchChar(ss, 0, pp, 0);
    }



    /**
     * 动态规划:
     *     dp[i][j] 表示 s 的前 i 个字符组成的字符串: s.subString(0,i)  是否能被 p 的前 j个字符组成的字符串 : p.subString(0,j) 匹配
     *
     * 1. 如果从左往右扫 (虽然1 和 c不 match，因为 * 可以消除前一个不匹配字符【出现0次】)
     *    s:11b
     *    p:c*a*b
     *     要一直考虑p的下一字符是否是*
     *
     * 2.因此选择从右往左扫描，s、p 串是否匹配，取决于：最右端是否匹配  且 剩余子串是否匹配（子问题）。
     *    只需考虑一次特殊符号问题（最右端）
     *
     *    1.当 p[j−1] 为普通字符, 若 s[i−1]==p[j−1]，则 dp[i][j]=dp[i−1][j−1]，否则匹配失败
     *    2.当 p[j−1]为'.'，则 dp[i][j] = dp[i−1][j−1]
     *    3.当 p[j−1]为'*'，
     *      (1)*用来匹配 0 次，则  dp[i][j]=dp[i][j−2]
     *      (2)*用来匹配多次（>=1），则dp dp[i][j]=dp[i−1][j]
     */
    public boolean isMatch1(String s, String p) {

        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();
        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];


        dp[0][0] = true; //两个空串一定是匹配的；
        dp[1][0] = false; //因为s有一个字符，p为空的时候一定是不匹配的

        for (int i = 0; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                if(ptr[j - 1] != '*') {
                    if(i > 0 && (str[i - 1] == ptr[j - 1] || ptr[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }else { //ptr[j - 1] == '*'
                    if(j > 1){
                        dp[i][j] |= dp[i][j - 2];   // *用来匹配 0 次，则 dp[i][j]=dp[i][j−2]
                    }
                    if(i > 0 && j > 1 && (str[i - 1] == ptr[j - 2] || ptr[j - 2] == '.')){
                        //*用来匹配多次（>=1）, dp[i][j]=dp[i−1][j]
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
