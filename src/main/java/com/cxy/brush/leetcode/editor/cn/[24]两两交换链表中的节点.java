package com.cxy.brush.leetcode.editor.cn;
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// todo 使用非递归改造代码
//
// 示例: 
//
// 给定 1->2->3->4->5->6->7->8->9, 你应该返回 2->1->4->3->6->5->8->7->9.
//为什么可以用递归?
//  因为我们最终可以实现的是两两节点进行交换
// 
// Related Topics 链表 
// 👍 548 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution24 {
    public ListNode swapPairs(ListNode head) {

        //退出条件: 没有节点或只有一个节点
       if(head==null || head.next==null){
           return head;
       }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        //处理好的指针
        return next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
