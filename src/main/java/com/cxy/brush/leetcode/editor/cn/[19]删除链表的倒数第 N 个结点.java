package com.cxy.brush.leetcode.editor.cn;
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1550 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;
import com.cxy.knowledge.list.removeNthFromEnd;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    /**
     * 方法一: 计算链表长度后，删除指定节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        //如共 5个节点，删除倒数第2个(正数第四个)，则先遍历 3次数
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLength(ListNode head){
        int length = 0;
        while (head!=null){
            length++;
            head = head.next;
        }
        return length;
    }


    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        //先进后出
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;

        //全部入栈 (每个栈元素都时一个链表)
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        //出栈n次
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        //目前栈顶元素即 待删除元素的前置节点prev
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 双指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode first = dummy;

        ListNode second = head;

        while(n!=0){
            second = second.next;
            n--;
        }
        //当 second到达末尾，即 first.next 为待删除节点
        while(second!=null){
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;

    }


    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        s.removeNthFromEnd(head,2);

    }


}
//leetcode submit region end(Prohibit modification and deletion)
