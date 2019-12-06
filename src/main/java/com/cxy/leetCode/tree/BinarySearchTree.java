package com.cxy.leetCode.tree;

public class BinarySearchTree {
    //整个树结构
    private Node tree;

    //查找
    public Node find(int data){
       Node p = tree;
       while(p!=null){
           if(data<p.data){
               p = p.left;
           }else if(data>p.data){
               p = p.right;
           }else {
               return p;
           }
       }
       return null;
    }

    public void insert(int data) {

        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;

        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    //右子节点为空,插入
                    p.right = new Node(data);
                    return;
                }
                //再遍历递归
                p = p.right;
            } else { // data < p.data

                if (p.left == null) {
                    //左子节点为空,插入
                    p.left = new Node(data);
                    return;
                }
                //再遍历递归
                p = p.left;
            }
        }

}


    public void delete(int data){
        //初始指向根节点，后期指向待删除节点
        Node p = tree;

        //pdad是p的父节点
        Node pdad = null;

        while (p != null && p.data != data) {
            pdad = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }

        if (p == null) {
            System.out.println("没有找到,删除失败");
            return;
        }
        //p有两个子节点
        if(p.left!=null && p.right!=null){
            //查找右子树中最小节点(往左找)
            Node minP = p.right;
            Node minPP =p; // minPP 表示 minP 的父节点
            while(minPP.left!=null){
                minPP = minP;
                minP = minP.left;
            }
            //将右子树中最小节点的值替换待删除节点的值
            p.data = minP.data;
            //删除minP,没有子节点了，放心删除
            p =minP;
            pdad = minPP;
        }

        //p没有或只有一个子节点
        Node child; // p 的子节点

        if (p.left != null){
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else{
            child = null;
        }
        if (pdad == null) {
            tree = child; // 删除的是根节点
        } else if (pdad.left == p) {
            pdad.left = child;
        } else{
            pdad.right = child;
        }

    }

    /**
     * 快速查找最小节点,一路向左
     * @return
     */
    public Node findMin() {
        if (tree == null) return null;
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * 快速查找最大节点,一路向右
     * @return
     */
    public Node findMax() {
        if (tree == null) return null;
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 中序遍历,即有序数据
     */
    public void inOrder(Node tree){
        if(tree == null){
            return;
        }

        inOrder(tree.left);

        System.out.println(tree.data);

        inOrder(tree.right);


    }


    private class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
