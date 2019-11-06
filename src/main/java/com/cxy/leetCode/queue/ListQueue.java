package com.cxy.leetCode.queue;
//基于链表的实现，我们同样需要两个指针：head 指针和 tail 指针。它们分别指向链表的第一个结点和最后一个结点。
// 入队时，tail->next= new_node, tail = tail->next；出队时，value = head.val, head = head->next。
public class ListQueue {

    private Node head = null;

    private Node tail =null;


    public static class Node{
        int val;
        Node next;
    public Node(int val) {
            this.val = val;
        }

    public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    //入队
    public void ebqueue(int value){
        //空
        if(head ==null){
            Node newNode = new Node(value);
            head = newNode;
            tail = newNode;
        }else{
            tail.next = new Node(value);
            tail = tail.next;
        }
    }

//出队
    public int ebqueue(){
        //空
        if(head ==null){
           return -1;
        }
        int value = head.val;
        head = head.next;
        if(head ==null){
            tail=null;
        }
        return value;
    }
}
