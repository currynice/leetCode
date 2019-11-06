package com.cxy.leetCode.list;


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
        ListNode prev = null;//前结点
        ListNode curr = head; //当前结点
        while (curr != null) {
            ListNode nextTemp = curr.next;//保存下一结点
            curr.next = prev; //反转
            prev = curr;
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
        if(head==null || null==head.next){
            return head;
        }
        ListNode p = reverseList2(head.next);
        //n k+1 的下一个节点指向 n k
        head.next.next = head;
        head.next = null;
        return p;
        //除非最后一次不存在上一层递归嵌套，否则都是有值的
    }

    //输入: 1->2->3->4->5->NULL
    //输出: 5->4->3->2->1->NULL
    public static void main(String args[]){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);

        a1.next = (a2);
        a2.next =(a3);
        a3.next =(a4);
        a4.next =(a5);

        reverseList(a1);
        ListUtil.printList(a1);
    }




}
