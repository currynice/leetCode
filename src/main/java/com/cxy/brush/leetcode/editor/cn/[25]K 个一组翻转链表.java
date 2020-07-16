package com.cxy.brush.leetcode.editor.cn;
//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 638 👎 0


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
class Solution25 {
    public  ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;

        while(end.next!=null){
            // start 到 end即为待反转区，一次反转k个元素
            for(int i=0;i<k&&end!=null;i++){
                end = end.next;
            }
            if(null ==end){
                break;// or return dummy.next;
            }
            ListNode start = pre.next;
            //更新未反转区
            ListNode unReverse = end.next;

            //将待反转区和未反转区隔开
            end.next = null;
            //待反转区开始反转
            pre.next = reverse(start);
            start.next = unReverse;
            pre = start;
            end = pre;
        }
        return dummy.next;

    }

//
//    public  void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2  = new ListNode(2);
//        node1.next = node2;
//
//        ListNode node3  = new ListNode(3);
//        node2.next = node3;
//
//        ListNode node4  = new ListNode(4);
//        node3.next = node4;
//
//
//        ListNode node5  = new ListNode(5);
//        node4.next = node5;
//
////        ListNode node6  = new ListNode(6);
////        node5.next = node6;
////
////        ListNode node7  = new ListNode(7);
////        node6.next = node7;
////
////        ListNode node8  = new ListNode(8);
////        node7.next = node8;
//
//
//
//        ListNode result = reverseKGroup(node1,3);
//        System.out.println();
//    }

    private  static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cuur = head;
        while(cuur!=null){
            //记录反转后的部分
            ListNode temp = cuur.next;
            //反转
            cuur.next = pre;
            pre = cuur;
            cuur = temp;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
