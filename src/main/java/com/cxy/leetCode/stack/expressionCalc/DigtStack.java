package com.cxy.leetCode.stack.expressionCalc;

//元素为double的栈，用以保存操作数
public class DigtStack {

    private Double[] items; // 数组
    private int count; // 栈中元素个数
    private int n; // 栈的大小
    // 初始化数组，申请一个大小为 n 的数组空间
    public DigtStack(int n) {
        this.items = new Double[n];
        this.n = n;
        this.count = 0;
    }
    // 入栈操作
    public boolean push(double item) {
        // 数组空间不够了，返回 false，入栈失败。
        if (count == n) return false;
        // 将 item 放到下标为 count 的位置，并且 count 加一
        items[count] = item;
        ++count;
        return true;
    }

    // 出栈操作
    public Double pop() {
        // 栈为空返回 null
        if (count == 0) return null;
        // 返回栈顶（下标为 count-1 的数组元素），并且栈中元素个数 count 减一
        Double tmp = items[count - 1];
        --count;
        return tmp;
    }



}
