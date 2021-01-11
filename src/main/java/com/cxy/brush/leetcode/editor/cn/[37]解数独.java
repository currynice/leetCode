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
        if(board==null || board.length==0){
            return;
        }

        fill(board,0,0);
    }

    private  boolean fill(char[][] board, int row, int col){
       // 当row等于board.length的时候表示数独最后一行全部读遍历完了，说明数独中的值是有效的，直接返回true
        if (row == board.length)
            return true;

        //如果当前行的最后一列也遍历完了，就从下一行的第一列开始。这里的遍历
        //顺序是从第1行的第1列一直到最后一列，然后第二行的第一列一直到最后
        //一列，然后第三行的……
        if (col == board.length)
            return fill(board, row + 1, 0);
        //如果当前位置已经有数字了，就不能再填了，直接到这一行的下一列
        if (board[row][col] != '.')
            return fill(board, row, col + 1);
        //如果上面条件都不满足，我们就从1到9种选择一个合适的数字填入到数独中
        for (char i = '1'; i <= '9'; i++) {
            //判断当前位置[row，col]是否可以放数字i，如果不能放再判断下
            //一个能不能放，直到找到能放的为止，如果从1-9都不能放，就会下面
            //直接return false
            if (!isValid(board, row, col, i))
                continue;
            //如果能放数字i，就把数字i放进去
            board[row][col] = i;
            //如果成功就直接返回，不需要再尝试了
            if (fill(board, row, col))
                return true;
            //否则就撤销重新选择
            board[row][col] = '.';
        }
        //如果当前位置[row，col]不能放任何数字，直接返回false
        return false;

    }

    //board[i][j] 能否填try_char
    private boolean isValid(char[][] board, int i, int j, char try_char) {
        for (int a = 0; a < 9; a++) {
            //当前列有没有和字符c重复的
            if (board[a][j] == try_char)
                return false;
            //当前行有没有和字符c重复的
            if (board[i][a] == try_char)
                return false;
            //当前的单元格内是否有和字符c重复的
            if (board[3 * (i / 3) + a / 3][3 * (j / 3) + a % 3] == try_char)
                return false;
        }
        return true;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
