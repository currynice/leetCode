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
    int[][] around = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};


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
            int m = grid.length,n = grid[0].length;
            int[][] res = new int[m][n];

            //存放可能是连通分量格子
            Deque<int[]> deque = new ArrayDeque<>();

            deque.addLast(new int[]{row,col});

            while(!deque.isEmpty()){
                //当前探测的连通分量
                int[] curr = deque.pollFirst();
                int x = curr[0];
                int y = curr[1];
                //上下左右进行探测
                //当前连通分量上下左右存在连通分量的个数
                int num = 0;
                for(int[] dire:around){
                    int dx = x+dire[0];
                    int dy = y+dire[1];

                    //corner check
                    if(dx<0 || dx>=m || dy<0 || dy>=n){
                        continue;
                    }
                    //判断(dx,dy)格子是否是连通分量
                    if(grid[dx][dy]!=grid[x][y]){
                        continue;
                    }else {
                        num++;
                    }
                    //如果当前已经探测过，跳过
                    if(res[dx][dy]!=0){
                        continue;
                    }
                    //可能是连通分量，放入deque
                    deque.addLast(new int[]{dx,dy});
                }

                //进行标色
                //对边界上color色，其余保持原色（grid[row][col]）
                res[x][y] = (num==4)?grid[row][col]:color;
            }

            //将bfs中遗漏的点补上原色
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (res[i][j] == 0){
                        res[i][j] = grid[i][j];
                    }
                }
            }

            return res;

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
