package com.cxy.brush.leetcode.editor.cn;
//给定一个 N 叉树，返回其节点值的后序遍历。
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其后序遍历: [5,6,3,2,4,1]. 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 86 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.Node;
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 在后序遍历中，先遍历一个节点的所有子节点再遍历这个节点本身。对于节点 u(子节点 v1, v2, v3 )，
 * 后序遍历过成为为:  [children of v1], v1, [children of v2], v2, [children of v3], v3, u，其中 [children of vk] 不包括 vk 本身。
 *
 * 将这个结果反转，可以得到 u, v3, [children of v3]', v2, [children of v2]', v1, [children of v1]'，其中 [a]' 表示 [a] 的反转。
 *
 * 和前序遍历类似，前序遍历中对子节点的遍历顺序是 v1, v2, v3，而后序是 v3, v2, v1。
 *
 * 使用一个栈来得到后序遍历。先把根节点入栈。当每次从栈顶取出一个节点 u 时，就把 u 的所有子节点顺序推入栈中。
 * 例如 u 的子节点从左到右为 v1, v2, v3，那么推入栈的顺序应当为 v1, v2, v3，这样就保证了下一个遍历到的节点（即 u 的第一个子节点 v3）出现在栈顶的位置。
 * 在遍历结束之后，把遍历结果反转，就可以得到后序遍历。
 *

 */
class Solution590 {
    public List<Integer> postorder0(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(res,root);
        return res;
    }

    //递归  后序: 子树-> 根
    private void helper(List<Integer> res,Node node){
        if(node!=null && !node.children.isEmpty()){
            for(Node per:node.children){
                helper(res,per);
            }
        }
        if(node!=null){
            res.add(node.val);
        }

    }

        public List<Integer> postorder(Node root) {
            LinkedList<Node> stack = new LinkedList<>();
            LinkedList<Integer> output = new LinkedList<>();
            if (root == null) {
                return output;
            }

            stack.add(root);
            while (!stack.isEmpty()) {
                Node node = stack.pollLast();
                output.addFirst(node.val);
                for (Node item : node.children) {
                    if (item != null) {
                        stack.add(item);
                    }
                }
            }
            return output;
        }

}
//leetcode submit region end(Prohibit modification and deletion)
