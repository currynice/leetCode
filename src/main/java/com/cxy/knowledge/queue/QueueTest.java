package com.cxy.knowledge.queue;

public class QueueTest {

    public static void main(String args[]){
//        ArrayQueue arrayQueue = new ArrayQueue(5);
//        //入队
//        System.out.println( arrayQueue.enqueue("1"));
//        System.out.println( arrayQueue.enqueue("2"));
//        System.out.println(arrayQueue.enqueue("3"));
//        System.out.println( arrayQueue.enqueue("4"));
//        System.out.println(arrayQueue.enqueue("5"));
//        System.out.println( arrayQueue.dequeue());
//        System.out.println( arrayQueue.dequeue());
//        System.out.println(arrayQueue.enqueue("6"));
//        System.out.println(arrayQueue.enqueue("7"));
//
//        //先进先出
//        System.out.println( "dequeue{1}"+arrayQueue.dequeue());
//        System.out.println( "dequeue{2}"+arrayQueue.dequeue());
//        System.out.println( "dequeue{3}"+arrayQueue.dequeue());
//        System.out.println("dequeue{4}" +arrayQueue.dequeue());
//        System.out.println( "dequeue{5}" +arrayQueue.dequeue());

//        ListQueue listQueue = new ListQueue();
//        //入队
//       listQueue.enqueue(1);
//        listQueue.enqueue(2);
//        listQueue.enqueue(3);
//        listQueue.enqueue(4);
//        listQueue.enqueue(5);
//
//        //先进先出
//        System.out.println( "dequeue{1}"+listQueue.dequeue());
//        System.out.println( "dequeue{2}"+listQueue.dequeue());
//        System.out.println( "dequeue{3}"+listQueue.dequeue());
//        System.out.println("dequeue{4}" +listQueue.dequeue());
//        System.out.println( "dequeue{5}" +listQueue.dequeue());
//        System.out.println("dequeue{6}" + listQueue.dequeue());

        CircularQueue cycleQueue = new CircularQueue(5);
        //入队,浪费一个空间
        System.out.println(cycleQueue.enqueue(1));
        System.out.println(cycleQueue.enqueue(2));
        System.out.println(cycleQueue.enqueue(3));
        System.out.println(cycleQueue.enqueue(4));
        System.out.println(cycleQueue.enqueue(5));

        //循环队列
        System.out.println( "dequeue{1}"+cycleQueue.dequeue());
        System.out.println( "dequeue{2}"+cycleQueue.dequeue());
        System.out.println( "dequeue{3}"+cycleQueue.dequeue());
        System.out.println("dequeue{4}" +cycleQueue.dequeue());
        //出队失败
        System.out.println( "dequeue{5}" +cycleQueue.dequeue());

    }
}
