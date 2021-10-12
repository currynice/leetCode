package com.cxy.brush.leetcode.editor.cn;
//存在一个按升序排列的链表，给你这个链表的头节点 head ，
// 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
// 返回同样按升序排列的结果链表。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序排列
//
// Related Topics 链表 双指针
// 👍 728 👎 0

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;
import com.cxy.knowledge.recursion.Test1;

import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)


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
class Solution82 {

    /**
     * 因为是删除，链表的头节点可能会被删除，因此需要使用 dummy 指向链表的头节点。
     *
     * cur指向dummy 开始，随后开始对链表进行遍历。
     *
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(null == head){
            return head;
        }

        ListNode dummy = new ListNode(-1,head);

        ListNode cur = dummy;


        while(cur.next !=null && cur.next.next !=null){

            if(cur.next.val == cur.next.next.val){
                int val_ = cur.next.val; //暂存值，后面只要等于这个值，全部删除
                while(cur.next!=null && cur.next.val == val_){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }

        }


        return dummy.next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
