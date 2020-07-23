package com.cxy.brush.leetcode.editor.cn;


//循环队列
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 判别队列为空的条件：front == rear;
 *
 * 判别队列为满的条件：(rear + 1) % capacity == front;。 当 rear 循环到数组的前面，要从后面追上 front，还差一格的时候，判定队列为满，否则再添加一个元素，队列将判定为空。
 *
 *
 */
class MyCircularQueue {

    private int[] data;
    //队列头部index
    private int front;

    //队列尾部 index,下一次添加队列时的插入index,该位置始终空出来
    private int rear;


    private int capacity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        front = 0;
        rear = 0;
        capacity = k+1;
        data = new int[capacity+1];
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(!isFull()){
            data[rear]=value;
            rear = (rear + 1) % capacity;
            return true;
        }
        return false;

    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(!isEmpty()){

            front = (front+1) % capacity;
            return true;
        }
        return false;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[(rear - 1 + capacity) % capacity];

    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (rear+1)%capacity == front;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)
