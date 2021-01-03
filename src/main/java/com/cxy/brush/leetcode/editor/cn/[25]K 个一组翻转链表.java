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
    public  ListNode reverseKGroup0(ListNode head, int k) {
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


    //栈(k个元素压栈，出栈即可得到反转链表)
    public ListNode reverseKGroup2(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (true) {
            int count = 0;
            ListNode tmp = head;
            while (tmp != null && count < k) {
                stack.add(tmp);
                tmp = tmp.next;
                count++;
            }
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()){
                p.next = stack.pollLast();
                p = p.next;
            }
            p.next = tmp;
            head = tmp;
        }
        return dummy.next;
    }



//    // 反转以 a 为头结点的链表
//    private  ListNode reverse(ListNode a) {
//        ListNode pre, cur, nxt;
//        pre = null; cur = a; nxt = a;
//        while (cur != null) {
//            nxt = cur.next;
//            // 逐个结点反转
//            cur.next = pre;
//            // 更新指针位置
//            pre = cur;
//            cur = nxt;
//        }
//        // 返回反转后的头结点
//        return pre;
//    }

    /** 反转区间 [a, b) 的元素 */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }





}
//leetcode submit region end(Prohibit modification and deletion)
