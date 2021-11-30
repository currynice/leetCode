package com.cxy.brush.leetcode.editor.cn;
//反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1092 👎 0


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
class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        //移动指针
        ListNode remove = head;
        while(remove!=null){
            //暂存next
           ListNode next = remove.next;
           //改变边
           remove.next = pre;

           //移动 remove 和 pre
           pre = remove;
           remove = next;
        }
        return pre;
    }


    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //结点next是null，则说明当前节点是反转后的头节点
        // 为null返回
        ListNode newHead;
        if (head == null || head.next == null) {
            return head;
        }
        newHead = reverseList2(head.next); // head.next 作为剩余部分的头指针
        // head.next 代表新链表的尾，将它的 next 置为 head，就是将 head 加到末尾了。
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
