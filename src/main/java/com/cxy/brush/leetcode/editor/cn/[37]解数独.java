package com.cxy.brush.leetcode.editor.cn;//编写一个程序，通过填充空格来解决数独问题。
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// 提示： 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 730 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void solveSudoku(char[][] board) {
        fill(0,0,board);
    }

    private  boolean fill(int row,int column,char[][] board){
        //填完了一行，列越界，填下一行
        if(column==9){
            row++;
            column=0;
            if(row==9){
                return true;
            }
        }
        if (!(".".equals((board[row][column]))))
            return fill(row, column + 1,board); // 不是空白格，递归填下一格

        for (int num = 1; num <= 9; num++) {           // 枚举出当前格的所有可填的选择
            if (hasConfilt(row, column, num,board)) continue; // 如果存在冲突，跳过这个选择
            board[row][column] =(char) (num+'0');;                   // 作出一个选择
            if (fill(row, column + 1,board))
                return true; // 如果基于它，填下一格，最后可以解出数独，直接返回true
            board[row][column] = '.';               // 否则恢复为空白格
        }
        return false; // 尝试了1-9，每个都往下递归，都不能做完，返回false

    }

    private boolean hasConfilt(int row,int column,int val,char[][] board){
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == val || board[row][i] == val) { // 行或列里有冲突
                return true;
            }
        }

            int subRowStart = (int) Math.floor(row / 3) * 3; // 对于小框，行有三种起始索引 0、3、6
            int subColStart = (int)  Math.floor(column / 3) * 3; // 对于小框，列有三种起始索引 0、3、6
            for (int a = 0; a < 3; a++) {              // 遍历所在的小框
                for (int b = 0; b < 3; b++) {
                    if (val == board[subRowStart + a][subColStart + b]) { // 发现了重复数
                        return true;
                    }
                }
            }
            return false; // 没有发生冲突

    }




}
//leetcode submit region end(Prohibit modification and deletion)
