package com.cxy.leetCode.stack.brower;


/**
 * 基于链表的链表栈(每个结点存储的是字符串)
 */
public class LinkedListBaseStack {

    //栈
    private Node stack;

    //大小
    private int size;

    public static class Node{
        private String value;
        private Node next;

        //初始化时用到的construct
        public Node(String value) {
            this(value,null);
        }

        //construct
        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }


    }

    public Node getStack() {
        return stack;
    }

    public int getSize() {
        return size;
    }


    /**
     * 从栈顶打印到栈底
     */
    public void printStack(){
        if (this.size==0) {
            return;
        }
        Node copy = this.stack;
        while(copy!=null){
            System.out.println(copy.value);
            copy = copy.next;
        }

    }


    public void clear(){
        this.stack = null;
        this.size = 0;
    }

    private Node createNewNode(String page,Node next){
        return new Node(page,next);
    }


    //封装的入栈方法
    public boolean instack(String page){
        Node now = createNewNode(page,stack);
        this.stack = now;
        this.size++;
        return true;
    }

    //封装的出栈方法
    public String outstack(){
        if(null==stack){
            return "empty stack";
        }
        String value = stack.value;
        stack = stack.next;
        if(size>0){
            size--;
        }
        return value;

    }


    public static void main(String args[]){
        LinkedListBaseStack stack = new LinkedListBaseStack();
        System.out.println("size:"+stack.getSize());
        stack.instack("1");
        stack.instack("2");
        stack.instack("3");
        stack.instack("4");
        stack.instack("5");
        System.out.println("size:"+stack.getSize());
        stack.printStack();
        System.out.println("get:");
        System.out.println(stack.outstack());
        System.out.println(stack.outstack());
        System.out.println(stack.outstack());
        System.out.println(stack.outstack());
        System.out.println(stack.outstack());
        System.out.println(stack.outstack());
    }

}
