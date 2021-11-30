package com.cxy.knowledge.stack;


import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

/**
 * 链表栈
 */
public class ListStack {

    private ListNode items = null; // 链表


    // 入栈操作
    public boolean push(int item) {
        ListNode newNode= new ListNode(item);
        if(items==null) {
            items = newNode;
        }else{
            newNode.next = items;
            items = newNode;
        }
        return true;
    }

    // 出栈操作,-1表示空栈
    public int pop() {
        // 栈为空，则直接返回 -1
        if (items == null) return -1;

        //先取值
        int value = items.val;
        //将item.next 赋值给 item
        items = items.next;
        return value;
    }


    public static void main(String args[]){
        ListStack stack = new ListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop()); //4
        System.out.println(stack.pop()); //3
        System.out.println(stack.pop()); //2
        System.out.println(stack.pop()); //1



    }

}
