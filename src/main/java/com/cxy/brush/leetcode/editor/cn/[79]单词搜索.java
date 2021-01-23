package com.cxy.brush.leetcode.editor.cn;//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 745 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution79 {
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(helper(board,i,j,word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    //board[i][j] 是否等于word.charAt(index), 且board[i][j] 的左，下，右，上是否存在任意一个等于 word.charAt(index+1)
    private boolean helper(char[][] board, int i, int j, String word, int index){
        if(index==word.length()){
            return true;//不用找了
        }

        // i,j非法,越界 或 board[i][j] 不等于word.charAt(index)
        if(i<0||i>=board.length||j<0||j>=board[0].length||word.charAt(index)!=board[i][j]){
            return false;
        }
        //暂存中心点
        char temp = board[i][j];

        //暂时将中心点置灰，因为同一个单元格内的字母不允许被重复使用
         board[i][j] ='*';

         //开始左，下，右，上 DFS探索 word.charAt(index+1)
         boolean founded = helper(board,i-1,j,word,index+1)||
                           helper(board,i,j+1,word,index+1)||
                           helper(board,i+1,j,word,index+1)||
                           helper(board,i,j-1,word,index+1);

         //不管找没找到，还原board[i][j]，因为后续该点还会使用到
        board[i][j] =temp;
        return founded;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
