package com.cxy.leetCode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cxy
 * @Date: 2020/4/15 22:42
 * @Description:
 */
public class RemoveNthFromEnd19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h = head;
        List<ListNode> noteArr = new ArrayList<>();
        int len = 0;
        while(h!=null){
            noteArr.add(h);
            h = h.next;
            len++;
        }
        if(n ==0){
            return head;
        }
        int remove = len-n;
        if(remove==0){
            return head.next;
        }
        ListNode r = noteArr.get(remove - 1);
        r.next = r.next.next;
        return head;
    }


    public static void main(String arsg[]){
        //1 2 3 4 5
        ListNode list1 = new ListNode(1);
        list1.next = (new ListNode(2));
        list1.next.next = (new ListNode(3));
        list1.next.next.next= (new ListNode(4));
        list1.next.next.next.next= (new ListNode(5));



        ListUtil.printList(removeNthFromEnd(list1,2));

    }
}
