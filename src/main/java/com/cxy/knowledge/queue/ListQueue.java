package com.cxy.knowledge.queue;


//基于链表的队列实现
public class ListQueue {

    //head 指针指向链表的第一个结点。
    private Node head = null;

    //tail 指针指向最后一个结点。
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

    //入队 tail->next= new_node, tail = tail->next
    public void enqueue(int value){
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

//出队,head出队,value = head.val, head = head->next。
    public int dequeue(){
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
