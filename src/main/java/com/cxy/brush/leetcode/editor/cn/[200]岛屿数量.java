//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 997 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class UnionFind {

        private int[] roots;  // i*n+j

        private int count = 0;

        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            this.roots = new int[m*n];
            //初始化
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        roots[i * n + j] = i * n + j;
                        ++count;
                    }
                }
            }
        }


        /**
         * 确定元素i属于哪个子集(找到元素i的root)
         * @param i
         * @return
         */
        public int findRoot(int i){
            int root = i;

            while(root !=roots[root]) {
                //找到i的root
                root = roots[root];
            }

            //路径压缩:上一步的节点都直接挂在root下
            while(i!=roots[i]){
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }

            return root;
        }

        /**
         * 两个元素是否属于同一子集
         * @return
         */
        public boolean connect(int a,int b){
            return findRoot(a)==findRoot(b);
        }


        /**
         * 将两个子集合并为一个集合
         * @return
         */
        public void union(int a,int b){
            int aroot = findRoot(a);
            int broot = findRoot(b);

            if (aroot != broot) {
                //进行合并
                roots[broot] = aroot;
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }


    /**
     * 并查集解法
     * 扫描整个网格。如果位置为 1，则将其与相邻四个方向上的 1 在并查集中进行合并。
     * @param grid
     * @return 岛屿的数量就是并查集中连通分量的数目。
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    //左
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }

                    //右
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }

                    //上
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }

                    //下
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
