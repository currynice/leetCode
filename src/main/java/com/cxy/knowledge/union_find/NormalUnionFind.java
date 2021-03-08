package com.cxy.knowledge.union_find;

/**
 * @Author: cxy
 * @Date: 2021/2/27 22:55
 * @Description:最朴素的并查集
 */
public class NormalUnionFind {

    private int[] roots;

    public NormalUnionFind(int N) {
        this.roots = new int[N];
        //初始化
        for(int i=0;i<N;i++){
            roots[i] = i;
        }
    }


    /**
     * 确定元素i属于哪个子集(找到元素i的root)
     * @param i
     * @return
     */
    private int findRoot(int i){
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
    private boolean connect(int a,int b){
        return findRoot(a)==findRoot(b);
    }


    /**
     * 将两个子集合并为一个集合
     * @return
     */
    private void union(int a,int b){
        int aroot = findRoot(a);
        int broot = findRoot(b);
        roots[broot] = aroot;
    }
}
