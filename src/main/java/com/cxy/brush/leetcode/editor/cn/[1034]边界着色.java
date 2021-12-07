package com.cxy.brush.leetcode.editor.cn;
//给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色
//。
//
// 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
//
// 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
//
// 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
//
//
//
// 示例 1：
//
//
//输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
//输出：[[3,3],[3,2]]
//
// 示例 2：
//
//
//输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
//输出：[[1,3,3],[2,3,3]]
//
// 示例 3：
//
//
//输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
//输出：[[2,2,2],[2,1,2],[2,2,2]]
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// 1 <= grid[i][j], color <= 1000
// 0 <= row < m
// 0 <= col < n
//
//
//
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵
// 👍 79 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {



    //对于格子(x,y)
    // 右: x+int[0][0],y+int[0][1]
    // 左: x+int[1][0],y+int[1][1]
    // 上：x+int[2][0],y+int[2][1]
    // 下: x+int[3][0],y+int[3][1]
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};


    // 以 (row, col) 为起点，当遍历到「连通分量的边界」格子，使用 color 上色
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        return bfsWay(grid,row,col,color);
    }

    /**
     * 广度优先法
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    private int[][] bfsWay(int[][] grid, int row, int col, int color){
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        Deque<int[]> d = new ArrayDeque<>();
        d.addLast(new int[]{row, col});
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int x = poll[0], y = poll[1], cnt = 0;
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[x][y] != grid[nx][ny]) continue;
                else cnt++;
                if (ans[nx][ny] != 0) continue;
                d.addLast(new int[]{nx, ny});
            }
            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0) ans[i][j] = grid[i][j];
            }
        }
        return ans;

    }


    public static void main(String[] args) {
        //：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
        ////输出：[[3,3],[3,2]]
        int[][] grid = new int[][]{{1,1},{1,2}};
        int[][]  res =  new Solution().colorBorder(grid,0,0,3);
        System.out.println();
    }


}
//leetcode submit region end(Prohibit modification and deletion)
