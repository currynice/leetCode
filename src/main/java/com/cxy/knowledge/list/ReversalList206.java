package com.cxy.knowledge.list;


//单链表 反转
//输入:       1->2->3->4->5->NULL
//输出: NULL<-1<-2<-3<-4<-5



public class ReversalList206 {
   //让前两个结点互换位置，当前结点指向前一，然后依次往后移动指针，直到第二个结点为空结束，再处理链表头尾即可。
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next==null ){
            //空链或只有一个结点，返回
            return head;
        }
        ListNode prev = null;//初始化前结点
        ListNode curr = head; //当前结点
        while (curr != null) {
            ListNode nextTemp = curr.next;//保存下一结点
            curr.next = prev; //反转
            prev = curr;//移动prev
            curr = nextTemp;
        }
        return prev;
    }

    //递归

    /**
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //结点next是null，则说明当前节点是反转后的头节点
        // 为null返回
        ListNode newHead;
        if (head == null || head.next == null) {
            return head;
        }
        newHead = reverseList(head.next); // head.next 作为剩余部分的头指针
        // head.next 代表新链表的尾，将它的 next 置为 head，就是将 head 加到末尾了。
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //输入: 1->2->3->4->5->NULL
    //输出: 5->4->3->2->1->NULL
    public static void main(String args[]){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);

        a1.next =(a2);
        a2.next =(a3);
        a3.next =(a4);
        a4.next =(a5);


        ListUtil.printList(reverseList(a1));
    }




}
