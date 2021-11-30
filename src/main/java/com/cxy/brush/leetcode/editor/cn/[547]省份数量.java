package com.cxy.brush.leetcode.editor.cn;//
// 
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。 
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。 
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 
//isConnected[i][j] = 0 表示二者不直接相连。 
//
// 返回矩阵中 省份 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 200 
// n == isConnected.length 
// n == isConnected[i].length 
// isConnected[i][j] 为 1 或 0 
// isConnected[i][i] == 1 
// isConnected[i][j] == isConnected[j][i] 
// 
// 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 501 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution547 {
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        NormalUnionFind unionFind = new NormalUnionFind(provinces);
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (unionFind.findRoot(i) == i) {
                circles++;
            }
        }
        return circles;
    }

    class NormalUnionFind {

        private int[] roots;

        public NormalUnionFind(int N) {
            this.roots = new int[N];
            //初始化
            for (int i = 0; i < N; i++) {
                roots[i] = i;
            }
        }


        /**
         * 确定元素i属于哪个子集(找到元素i的root)
         *
         * @param i
         * @return
         */
        private int findRoot(int i) {
            int root = i;

            while (root != roots[root]) {
                //找到i的root
                root = roots[root];
            }

            //路径压缩:上一步的节点都直接挂在root下
            while (i != roots[i]) {
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }

            return root;
        }

        /**
         * 两个元素是否属于同一子集
         *
         * @return
         */
        private boolean connect(int a, int b) {
            return findRoot(a) == findRoot(b);
        }


        /**
         * 将两个子集合并为一个集合
         *
         * @return
         */
        private void union(int a, int b) {
            int aroot = findRoot(a);
            int broot = findRoot(b);
            roots[broot] = aroot;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
