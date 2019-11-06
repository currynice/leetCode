package com.cxy.leetCode.queue;

//数组实现顺序队列
public class ArrayQueue {
    //数组
    private String[] items;
    //数组大小
    private int n = 0;

    //队头下标
    private int head;

    //队尾下标
    private int tail;

    //初始化时指定容量
    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean enqueue(String item){
        //tail = n表示队列已满,末尾没有空间
        if (tail == n){
            //同时head==0,表示真的满了
            if(head == 0){
                return false;
            }
            //数据搬移,head-tail -> 0- (tail-head)
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


    //出队，队头取出
    public String dequeue(){
        //空队列
        if(head == tail){
            return null;
        }

        String value = items[head];
        ++head;
        return value;
    }

}
