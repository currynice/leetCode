package com.cxy.knowledge.list;

//链表
public class MyLinkedList {
    private ListNode first;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(getByIndex(index)==null)
            return -1;
        return getByIndex(index).val;

    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        // 如果index为负数或0，插入到头部(将first作为新头结点的next)
        if (index <= 0) {
            first = new ListNode(val, first);
        } else {
            //查出前结点,插入
            ListNode prev = getByIndex(index - 1);
            if (prev == null) return;
            prev.next = new ListNode(val, prev.next);
        }
        //链表长度增加
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        //index不合理
        if(!isValidIndex(index)){
            return;
        }else if(0==index){
            //删除第一个结点
            first = first.next;
        }else{
            ListNode prev = getByIndex(index-1);
            prev.next = prev.next.next;
        }
        size--;

    }
    /**根据索引得到结点*/
    private ListNode getByIndex(int index){

        //找不到
        if(!isValidIndex(index)) return null;
        //从index=0开始找
        ListNode node = first;
        for(int i=0;i<index;i++){
            node = node.next;
        }
        return node;
    }

    /**判断索引是否合理*/
    private boolean isValidIndex(int index){
       return  !(index<0 || index>size-1);
    }




    /** ListNode 节点*/
    private class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val,ListNode next) {
            this.val = val;
            this.next = next;
        }

    }


}



