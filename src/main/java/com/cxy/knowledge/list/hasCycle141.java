package com.cxy.knowledge.list;




import java.util.HashSet;
import java.util.Set;

//给定一个链表，判断链表中是否有环
public class hasCycle141 {

    /**
     *
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head){
        Set<ListNode> hash = new HashSet<>();

        while (head != null){
            if(hash.contains(head)){
                return true;
            }else {
                hash.add(head);
            }
            //检测下一个
            head = head.next;
        }
        //遍历到尾结点,无环
        return false;
    }


    /**
     * 快慢指针,
     * 1：链表无环，快的一定先到(先到;next==null)
     * 1：链表有环，快的一定会追上慢的
     * @return
     */
    public static boolean hasCycle(ListNode head){
        if(null==head || null==head.next){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if(null == fast || null==fast.next){
              return false;
            }
            slow = slow.next;//每次一步
            fast = fast.next.next; //每次两步
        }
        //追上了，有环
        return true;
    }



    public static void main(String args[]){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);
        ListNode a8 = new ListNode(8);
        ListNode a9 = new ListNode(9);
        ListNode a10 = new ListNode(10);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;
        a7.next = a8;
        a8.next = a9;
        a9.next = a10;
//        a10.setNext(a7);//有环

        System.out.println(hasCycle1(a1));



    }
}
