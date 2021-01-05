package com.cxy.brush.leetcode.editor.cn.public_class;

/**
 * Description: 二叉树节点 </br>
 * Date: 2020/7/28 20:53
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
