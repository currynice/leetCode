package com.cxy.brush.leetcode.editor.cn;
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
// 示例: 
//
// 给定 1->2->3->4->5->6->7->8->9, 你应该返回 2->1->4->3->6->5->8->7->9.
//为什么可以用递归?
//  因为我们最终可以实现的是两两节点进行交换
// 
// Related Topics 链表 
// 👍 548 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution24 {
    public ListNode swapPairs1(ListNode head) {

        //退出条件: 没有节点或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        //...假设 head.next 交换好了
        head.next = swapPairs(next.next);
        // 和当前节点交换
        next.next = head;
        //返回： 交换完的子节点（以及其链表）
        return next;
    }


    /**
     * 非递归写法
     *
     0:    0 -> 1 —> 2 -> 3 -> 4 -> 5
          pre
          dump

     {
     start = dump.next
     end = dump.next.next
     }

     1:    0 ->   1    —>   2   ->  3 -> 4 -> 5
           pre
           dump   start     end


     {
     dump.next = end
     }

     0 ->   2   ->  3 -> 4 -> 5
     pre
     dump   end

     1  —>   2   ->  3 -> 4 -> 5
     start

     {
     start.next = end.next
     }

     1   —>  3 -> 4 -> 5
     start

     {
     end.next = start
     }

     0   ->   2   ->  1  —>   3 -> 4 -> 5
     pre
     dump     end     start

     {
     dump = start
     }

     0   ->   2   ->  1  —>   3 -> 4 -> 5
     pre     end      start
                      dump


     */
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0,head);
        ListNode dump = pre;
        // 即将交换的两个节点必须都存在
        while (dump.next != null && dump.next.next != null) {
            ListNode start = dump.next;
            ListNode end = dump.next.next;
            dump.next = end;
            start.next = end.next;
            end.next = start;
            dump = start;
        }
        return pre.next;
    }


    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        new Solution24().swapPairs(head);

    }

}
//leetcode submit region end(Prohibit modification and deletion)
