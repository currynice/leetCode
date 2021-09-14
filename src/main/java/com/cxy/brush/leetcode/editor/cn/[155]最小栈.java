package com.cxy.brush.leetcode.editor.cn;

//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 609 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {


    /*
     * push(e)	addFirst(e)
     * pop()	removeFirst()
     * peek()	peekFirst()
     */
    Deque<Integer>  dataStack;

    //辅助
    Deque<Integer>  minStack;


//      对于辅助栈：    push:    ) 辅助栈为空的时候，顺带放入新进来的数；
//
//                              ) 新来的数小于或者等于辅助栈栈顶元素的时候，也放入。 出栈的时候，连续相等的并且是最小值的元素要同步出栈；
//
//                      pop：   出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈。

    /** initialize your data structure here. */
    public MinStack() {
        dataStack =  new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        dataStack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        if (!dataStack.isEmpty()) {
            // 这里 拆箱成 int 类型并 使用 "==" 运算符 ，或者使用 equals 方法比较Integer 类型
            int top = dataStack.pop();
            if(top == (int)minStack.peek()){
                minStack.pop();
            }
        }
    }
    
    public int top() {
        if(!dataStack.isEmpty()){
            return dataStack.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }
    
    public int getMin() {
        if(!minStack.isEmpty()){
            return minStack.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public static void main(String[] args) {
      MinStack obj = new MinStack();
        obj.push(512);
        obj.push(-1024);
        obj.push(-102);
        obj.push(512);

        obj.pop();
        int param_1 = obj.getMin();
        obj.pop();
        int param_3 = obj.getMin();
        obj.pop();
        int param_4 = obj.getMin();
        System.out.println();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
