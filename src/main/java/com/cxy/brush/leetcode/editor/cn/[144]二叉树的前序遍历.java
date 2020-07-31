package com.cxy.brush.leetcode.editor.cn;//给定一个二叉树，返回它的 前序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 322 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.TreeNode;
//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return res;
    }

    private void helper(TreeNode node, List<Integer> res) {
            //根 左 右
        if(node!=null){
            res.add(node.val);
            if(node.left!=null){
                helper(node.left,res);
            }
            if(node.right!=null){
                helper(node.right,res);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
