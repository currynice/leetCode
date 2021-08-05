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

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

import java.util.List;
//leetcode submit region begin(Prohibit modification and deletion)


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
        // 进位, 0或1 ，因为 两数相加 ，最多 9+9+1 =19
        int carry = 0;

        //最后结果的前一节点
        ListNode pre = new ListNode(-1);

        ListNode curr = pre;

        while(l1!=null || l2!=null){
            int para1 = (l1==null)?0:l1.val;
            int para2 = (l2==null)?0:l2.val;
            int sum = para1 +para2+carry;

            carry = (sum>=10)?1:0;
            sum = sum%10;

           curr.next = new ListNode(sum);
           curr = curr.next;

            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
            //进位 若为1 ,需要加到结果链表最后，否则，l1,l2都为nUll,这个1不在结果当中
            if(carry == 1){
                curr.next = new ListNode(1);
            }
        }
        return pre.next;

    }




    //-------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        Solution2 solution2 = new Solution2();
         ListNode s = solution2.addTwoNumbers(l1,l2);
       // 3,342 + 465 = 3,807
        printList(s);
    }

    /**
     * 打印链表，因为各位逆序存储，因此反转后输出
     */
    private static void printList(ListNode head){
        ListNode cur = reverse(head);
        while(cur!=null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


    private  static ListNode reverse(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = head;// 上一结点
        ListNode cur = head.next;// 当前结点
        ListNode tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next = pre;// 反转指针域的指向

            // 指针往下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next=null;

        return pre;
    }



}

//leetcode submit region end(Prohibit modification and deletion)
