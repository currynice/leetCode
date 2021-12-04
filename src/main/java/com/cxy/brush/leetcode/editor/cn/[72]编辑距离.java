package com.cxy.brush.leetcode.editor.cn;//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1423 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution72 {

    /**
     * DP[i][j] 定义为word1前i个字符变更为word2前j个字符，最少需要的操作次数
     *
     * result = DP[word1.length][word2.length]
     *
     * DP 方程:
     *  当 word1[i] = word[j] , DP[i][j] = DP[i-1][j-1]
     *  当 word1[i] != word[j] , INSERT: DP[i][j] = DP[i-1][j] , DELETE: DP[i][j] = DP[i][j-1]  ,REMOVE: DP[i][j] = DP[i-1][j-1]
     *     综上 DP[i][j] = MIN(DP[i-1][j] ,DP[i][j-1] ,DP[i-1][j-1] ) +1
     *
     *
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        Integer result= 0;

        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP定义
        int[][] DP = new int[n + 1][m + 1];

        // 初始化
        for (int i = 0; i < n + 1; i++) {
            DP[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            DP[0][j] = j;
        }


        //向上推，直到只剩下第一个节点的最短路径和
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    DP[i][j] = DP[i-1][j-1];
                }else {
                    int INSERT = DP[i-1][j];
                    int DELETE = DP[i][j-1];
                    int REMOVE = DP[i-1][j-1];

                    DP[i][j] = Math.min(Math.min(INSERT,DELETE),REMOVE)+1;
                }
            }
        }
        return  DP[n][m];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
