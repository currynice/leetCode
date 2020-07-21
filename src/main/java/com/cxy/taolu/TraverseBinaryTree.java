package com.cxy.taolu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: 二叉树遍历套路   <br>
 * Date: 2020/7/21 9:16  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class TraverseBinaryTree {

    // 节点分隔字符
    public static final String SEP = ",";
    public static final String NULL = "#";

     class TreeNode{

        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        TraverseBinaryTree traverseBinaryTree = new TraverseBinaryTree();
        TreeNode root = traverseBinaryTree.treeInit();

        //test for 前序
//        StringBuilder sb = new StringBuilder();
//        traverseBinaryTree.preTrverse(root,sb);
//        String casSting = sb.toString();
//        System.out.println(casSting);
//
//        /* 根据cas字符串还原二叉树 */
//        String[] nodesStr = casSting.split(SEP);
//        LinkedList<String> nodes = new LinkedList(Arrays.asList(nodesStr));
//        TreeNode build = traverseBinaryTree.preBuild(nodes);
//        System.out.println();

        //test for 后序
//        StringBuilder sb2 = new StringBuilder();
//        traverseBinaryTree.postTrverse(root,sb2);
//        String casSting2 = sb2.toString();
//        System.out.println(casSting2);
//
//        /* 根据cas字符串还原二叉树 */
//        String[] nodesStr2 = casSting2.split(SEP);
//        LinkedList<String> nodes2 = new LinkedList(Arrays.asList(nodesStr2));
//        TreeNode build2 = traverseBinaryTree.postBuild(nodes2);
//        System.out.println();

        //test for 层级遍历
        StringBuilder sb3 = new StringBuilder();
        traverseBinaryTree.levelTraverse(root,sb3);
        String casSting3 = sb3.toString();
        System.out.println(casSting3);

        String[] nodesStr3 = casSting3.split(SEP);
        TreeNode build3 = traverseBinaryTree.levelBuild(nodesStr3);
        System.out.println();
    }

    /**
     * 前序框架
     *  void traverse(TreeNode root) {
     if (root == null) return;
     -- 前序遍历的代码 --
     traverse(root.left);
     traverse(root.right);
     }
     */

    //前序遍历
    private  void preTrverse(TreeNode root, StringBuilder sb){

        /* 将二叉树打平为字符串 */
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.data).append(SEP);
            preTrverse(root.left, sb);
            preTrverse(root.right, sb);
    }

    //前序还原
    private  TreeNode preBuild(LinkedList<String> nodes){
            if (nodes.isEmpty()) return null;
            /****** 前序遍历位置 ******/
            // 第一个为根节点
            String first = nodes.removeFirst();
            if(first.equals(NULL)) return null;
            TreeNode root = new TreeNode(Integer.parseInt(first));
            /***********************/
            root.left = preBuild(nodes);
            root.right = preBuild(nodes);
            return root;
    }
    /**
      * 后序框架
      *  void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        traverse(root.right);
        -- 后序遍历的代码 --
      }
     */
    //后序遍历
    private  void postTrverse(TreeNode root, StringBuilder sb){

        /* 将二叉树打平为字符串 */
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        postTrverse(root.left, sb);
        postTrverse(root.right, sb);
        sb.append(root.data).append(SEP);
    }

    private  TreeNode postBuild(LinkedList<String> nodes){
        if (nodes.isEmpty()) return null;
        // 最后一个为根节点
        String last = nodes.removeLast();
        if(last.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(last));
        root.right = postBuild(nodes);
        root.left = postBuild(nodes);
        return root;
    }




    /**
     * 中序框架
     *  void traverse(TreeNode root) {
     -- 中序遍历的代码 --
     traverse(root.left);
     if (root == null) return;
     traverse(root.right);
     }
     */
    //中序遍历
    private  void midTrverse(TreeNode root, StringBuilder sb){

        /* 将二叉树打平为字符串 */
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        midTrverse(root.left, sb);
        sb.append(root.data).append(SEP);
        midTrverse(root.right, sb);
    }

    //中序遍历还原因为不知道确切 root 节点位置，无法还原。


    /**
     * 层级遍历框架
     * while (!q.isEmpty()) {
     *     TreeNode cur = q.poll();
     *
     *     if (cur.left != null) {
     *         q.offer(cur.left);
     *     }
     *
     *     if (cur.right != null) {
     *         q.offer(cur.right);
     *     }
     * }
     */

    //层级遍历
    private void levelTraverse(TreeNode root,StringBuilder sb) {
        if (root == null) return;
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
           /******层级遍历代码位置***********/
            if (cur == null){
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.data).append(SEP);
            /*****************/

                //为了反序列化，记录null
                q.offer(cur.left);
                q.offer(cur.right);
        }
    }


    private TreeNode levelBuild(String[] nodeStrs) {
        if(!(nodeStrs.length>0)) return null;
        //queue只保存已经处理完的相对父节点(根节点和每层的父节点)
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodeStrs[0]));
        queue.offer(root);
        for(int i=1;i<nodeStrs.length;i++){
            TreeNode parent = queue.poll();//一般不会是null
            // 父节点左子节点
            String left = nodeStrs[i++];
            if (!left.equals(NULL)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                queue.offer(parent.left);
            }
            // 父节点对应的右侧子节点的值
            String right = nodeStrs[i++];
            if (!right.equals(NULL)) {
                parent.right = new TreeNode(Integer.parseInt(right));
                queue.offer(parent.right);
            }
        }

        return root;
    }













    private  TreeNode treeInit(){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        root.left = node2;
        root.right = node3;
        node2.right = node4;

        return root;
     }
}
