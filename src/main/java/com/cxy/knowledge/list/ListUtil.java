package com.cxy.knowledge.list;

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

public class ListUtil {

    //输出链表结构
    public static void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

}
