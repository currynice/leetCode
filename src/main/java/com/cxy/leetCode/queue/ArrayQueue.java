package com.cxy.leetCode.queue;

/**
 * 数组实现顺序队列 假设此时队列状态: [null,"a","b","c","d",null,null,null]
 */
public class ArrayQueue {
    //数组
    private String[] items;
    //数组大小
    private int n ;

    //队头下标 1
    private int head;

    //队尾下标 5,指向队列元素的后一个index,避免head与tail相冲突,head==tail表示空队列
    private int tail;

    //初始化时指定容量
    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean enqueue(String item){
        //tail = n表示末尾没有空间,队满
        if (tail == n){
            //同时head==0,表示真的满了
            if(head == 0){
                return false;
            }
            //入队时数据搬移,head-tail -> 0- (tail-head)
            for(int i = head;i<tail;++i){
                items[i-head] = items[i];
            }
            tail -= head;
            head = 0;

        }
        items[tail] = item;
        ++tail;
        return true;
    }


    //出队，队头取出  O(1)
    public String dequeue(){
        //队空
        if(head == tail){
            return null;
        }

        String value = items[head];
        ++head;
        return value;
    }
}
