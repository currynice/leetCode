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

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution25 {


    /**
     * 解法一： k个一组，递归
     * @param head
     * @param k
     * @return
     */
    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        // 设置 区间 [start, end) , end-start <= k
        ListNode start, end;
        start = end = head;
        for (int i = 0; i < k; i++) {
            // 如果 [a，b）)不足k个，不进行反转了，结束
            if (end == null) {
                return head;
            }
            end = end.next;
        }

        // 反转这 k 个元素
        ListNode newHead = reverse(start, end);
        // 递归反转后续链表并连接起来
        start.next = reverseKGroup(end, k);

        return newHead;
    }

    /**
     * 解法二.非递归方式
     * @param head
     * @param k
     * @return
     */
    public  ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(0,head);

        ListNode pre = dummy;

        ListNode end = dummy;

        while(end.next!=null){
            // 一次性要反转k个元素 ,考虑 k >end 的情况
            for(int i=0;  i<k&&end!=null;i++){
                end = end.next;
            }
            if(null ==end){
                //不满k个就不用反转了
                break;// or return dummy.next;
            }
            ListNode start = pre.next;
            //保存未反转区起点
            ListNode unReverse = end.next;

            //断开 待反转链表 和 未反转链表
            end.next = null;
            //反转待反转链表
            pre.next = reverse(start);


            //移动指针
            start.next = unReverse;

            pre = start;

            end = pre;
        }
        return dummy.next;

    }



    // 反转以 a 为头结点的链表
    private  ListNode reverse(ListNode a) {
        ListNode pre = null;
        ListNode cur = a;

        while (cur != null) {
            ListNode nxt = cur.next;
            // 结点反转
            cur.next = pre;

            // 移动指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }


    /**
     * 反转区间 [a, b) 的元素   1 > 2 > 3 > 4 > 5 > null,  反转[1，3) ，null < 1 < 2  3->4->null
     */
    private static ListNode reverse(ListNode start, ListNode end) {
        ListNode pre = null;
        ListNode cur = start;
        // while 终止的条件改为 curr!=b
        while (cur != end) {

            ListNode nxt = cur.next;

            cur.next = pre;

            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);
        a.next.next.next.next = new ListNode(5);


        ListNode b = a.next.next;


        ListNode result = reverse(a,b);

        System.out.println(result);

    }








}
//leetcode submit region end(Prohibit modification and deletion)
