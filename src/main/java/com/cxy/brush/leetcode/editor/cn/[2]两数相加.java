package com.cxy.brush.leetcode.editor.cn;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
// Related Topics 链表 数学
// 👍 4579 👎 0


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
class Solution2 {
    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位 0 or 1 ，因为9+9+1 = 19，最多进一位
        int  carry = 0;
        //预先仿真节点, 保存头节点，防止指针丢失
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        //计算直至两个链表都为null
        while((null!=l1) || (null!=l2)){
            int para1 = null==l1?0:l1.val;
            int para2 = null==l2?0:l2.val;

            int sum = para1 + para2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            //结果链表移动
            cur = cur.next;

            //参数链表移动
            if(null != l1){
                l1 = l1.next;
            }

            if(null != l2){
                l2 = l2.next;
            }
        }

        //进位 若为1 ,需要加到结果链表中
        if(carry == 1){
            cur.next = new ListNode(1);
        }
        return pre.next;
    }

}

//leetcode submit region end(Prohibit modification and deletion)
