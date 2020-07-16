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
           ListNode temp = remove.next;
           remove.next = pre;
           pre = remove;
           remove = temp;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
