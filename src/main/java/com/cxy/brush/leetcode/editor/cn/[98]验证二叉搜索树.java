package com.cxy.brush.leetcode.editor.cn;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 879 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;
import com.cxy.brush.leetcode.editor.cn.public_class.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution98 {




    /**
     * in-order 中序遍历(左根右)后，判断结果ArrayList是否为升序
     * @param root
     * @return
     */
//    public  boolean isValidBST(TreeNode root) {
//        /* 将二叉树打平 */
//        if (root == null) {
//            return true;
//        }
//        List<Integer> result = new ArrayList<>();
//        midTrverse(root,result);
//        System.out.println(Arrays.toString(result.toArray()));
//
//        //判断是否是升序
//        for(int i=1;i<result.size();i++){
//            if(result.get(i) <= result.get(i-1)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    //中序遍历
//    private   void midTrverse(TreeNode root, List<Integer> result){
//        if(root.left!=null){
//            midTrverse(root.left, result);
//        }
//        result.add(root.val);
//        if(root.right!=null){
//            midTrverse(root.right, result);
//        }
//    }




    /**
     * in-order 中序遍历(左根右)后判断当前节点是否大于中序遍历的前一个节点
     * @param root
     * @return
     */

    private long preVal = Long.MIN_VALUE;

    public  boolean isValidBST(TreeNode root) {
        /* 将二叉树打平 */
        if (root == null) {
            return true;
        }
        // 左
        if (!isValidBST(root.left)) {
            return false;
        }
        // 中：当前节点小于等于前一个节点，不满足BST
        if (root.val <= preVal) {
            return false;
        }
        preVal = root.val;
        // 右
        return isValidBST(root.right);
    }


//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(2,new TreeNode(1),new TreeNode(3));
//        System.out.println(isValidBST(root));
//    }

//    /**
//     * 递归
//     * @param root
//     * @return
//     */
//    public boolean isValidBST(TreeNode root) {
//        return isValid(root,null,null);
//    }
//
//    /**
//     * node.val > min && node.val < max
//     * @param node
//     * @param min  树node 的val 应该大于的值
//     * @param max  树node 的val 应该小于的值
//     * @return
//     */
//    private boolean isValid(TreeNode node,Integer min ,Integer max){
//        if(node ==null){
//            return true;// 空树是BST
//        }
//
//        if(min!=null && node.val<=min){
//            return false;
//        }
//        if(max!=null && node.val>=max){
//            return false;
//        }
//
//        return isValid(node.left,min,node.val) && isValid(node.right,node.val,max);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
