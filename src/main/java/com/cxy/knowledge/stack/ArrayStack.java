package com.cxy.knowledge.stack;

//数组栈(先进后出)
public class ArrayStack {

    private String[] items; // 数组
    private int count; // 栈中元素个数
    private int n; // 栈的大小
    // 初始化数组，申请一个大小为 n 的数组空间
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }
    // 入栈操作
    public boolean push(String item) {
        // 数组空间不够了，返回 false，入栈失败。
        if (count == n) return false;
        // 将 item 放到下标为 count 的位置，并且 count 加一
        items[count] = item;
        ++count;
        return true;
    }

    // 出栈操作
    public String pop() {
        // 栈为空返回 null
        if (count == 0) return null;
        // 返回栈顶（下标为 count-1 的数组元素），并且栈中元素个数 count 减一
        String tmp = items[count - 1];
        --count;
        return tmp;
    }


    public static void main(String args[]){
        ArrayStack stack = new ArrayStack(3);
        System.out.println(stack.push("1"));
        System.out.println(stack.push("2"));
        System.out.println(stack.push("3"));
        System.out.println(stack.push("4"));

        System.out.println(stack.pop()); //3
        System.out.println(stack.pop()); //2
        System.out.println(stack.pop()); //1
        System.out.println(stack.pop()); //null



    }


}
