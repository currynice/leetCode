package com.cxy.brush.leetcode.editor.cn;
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 741 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.TreeNode;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution102 {

    /**
     * 一.BFS
     */
//    public  List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (root == null)
//            return result;
//
//        // 初始化队列，将 root 加入队列
//        Queue<TreeNode> q = new LinkedList<>();
//        q.offer(root);
//
//        while (!q.isEmpty()) {
//
//            List<Integer> thisLevel = new ArrayList<>();
//
//            //确定好当前行走的步数，添加下一级元素，不影响当前级别的输出
//            Integer thisLevelSize = q.size();
//            for(int i=0;i<thisLevelSize;i++){
//                thisLevel.add(q.peek().val);
//                TreeNode curr = q.poll();
//                if(curr.left!=null){
//                    q.offer(curr.left);
//                }
//                if(curr.right!=null){
//                    q.offer(curr.right);
//                }
//            }
//            result.add(thisLevel);
//
//        }
//        return result;
//    }

    /**
     * 二.DFS  (深度优先,并记录每个元素的level，添加到对应的result[])
     */
    public  List<List<Integer>>  levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        dfs(root,0,result);
        return result;
    }

    private  void dfs(TreeNode node ,int level, List<List<Integer>> result){
        if(node==null){
            return;
        }

        //result.size即当前级数，当遍历到一个新的深度 level，而最终结果 res 中还没有创建 level 对应的列表时，应该在 res 中新建一个列表用来保存该 level 的所有节点
        if(result.size() < level+1){
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);
        dfs(node.left,level+1,result);
        dfs(node.right,level+1,result);


    }




//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        TreeNode v9 = new TreeNode(9);
//        TreeNode v20 = new TreeNode(20);
//        TreeNode v15 = new TreeNode(15);
//        TreeNode v7 = new TreeNode(7);
//        root.left = v9;
//        root.right = v20;
//        v20.left = v15;
//        v20.right = v7;
//        levelOrder2(root);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
