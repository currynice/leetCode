//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 222 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution52 {

    public int totalNQueens(int n) {
        Set<Integer> columns = new HashSet<>();
        //row -1 行 所有右上方向已经使用的
        Set<Integer> pie = new HashSet<>();
        //row -1 行 所有左上方向已经使用的
        Set<Integer> na = new HashSet<>();
        return backtrack(n, 0, columns, pie, na);
    }

    private int backtrack(int n, int row, Set<Integer> columns, Set<Integer> pie, Set<Integer> na) {
        //成功找到一组解,count +1
        if (n == row) {
            return 1;
        } else {
            int count = 0;
            //查看当前行每列对应的位置
            for (int i = 0; i < n; i++) {
                //当前位置被皇后占用
                if (columns.contains(i)) {
                    continue;
                }

                if (pie.contains(row + i)) {
                    continue;
                }

                if (na.contains(row - i)) {
                    continue;
                }
                columns.add(i);
                pie.add(row + i);
                na.add(row - i);
                //向下探索
                count += backtrack(n, row + 1, columns, pie, na);
                //探索后，不管由于没有
                columns.remove(i);
                pie.remove(row + i);
                na.remove(row - i);
            }
            return count;
        }
    }

}


//leetcode submit region end(Prohibit modification and deletion)
