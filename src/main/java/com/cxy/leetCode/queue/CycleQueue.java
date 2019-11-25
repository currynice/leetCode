package com.cxy.leetCode.queue;

/**
 * 循环队列,避免入队时数据搬移
 */
public class CycleQueue {

    private int[] items ;

    private int head;

    private int tail;

    private int capacity;

    public CycleQueue(int capacity) {
        this.items = new int[capacity];
        this.capacity = capacity;
    }

    //入队
    public boolean enqueue(int item){
    //如果队满
        if((tail+1)%capacity==head){
            return false;
        }
        items[tail] = item;
        if(tail==capacity-1){
          tail = 0;
        }else{
            ++tail;
        }
        return true;
    }


    //出队，队头取出
    public Integer dequeue(){
        //空队列
        if(head == tail){
            return null;
        }

        int value = items[head];
        if(head==capacity-1){
            head = 0;
        }else{
            ++head;
        }
        return value;
    }
}
