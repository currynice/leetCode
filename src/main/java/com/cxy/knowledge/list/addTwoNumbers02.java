package com.cxy.knowledge.list;

public class addTwoNumbers02 {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1!=null || l2!=null){
            int value1 = l1==null?0:l1.val;
            int value2 = l2==null?0:l2.val;
            int sum = (value1 + value2+carry);
            carry = sum/10;
            sum = sum %10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1!=null)
            l1 = l1.next;
            if (l2!=null)
            l2 = l2.next;

        }

        if(carry==1){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }



    //342 + 465 = 807
    public static void main(String arsg[]){
        //2->4->3
        ListNode list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(3);

        //5->6->4
        ListNode list2 = new ListNode(5);
        list2.next =new ListNode(6);
        list2.next.next =new ListNode(4);

        //7->0->8
        ListUtil.printList(addTwoNumbers(list1,list2));

    }
}
