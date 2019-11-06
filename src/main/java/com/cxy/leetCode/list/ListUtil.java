package com.cxy.leetCode.list;

import com.cxy.leetCode.list.ListNode;

public class ListUtil {

    //输出链表结构
    public static void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

}
