package com.cxy.brush.leetcode.editor.cn;
//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
//
//
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
//
//
//
// 示例:
//
// 输入: [2,1,5,6,2,3]
//输出: 10
// Related Topics 栈 数组
// 👍 806 👎 0
import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution84 {


    /**
     * 右边要比当前严格小，左边也要比当前高度严格小(满足)
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {


        /*
         * 存放数组heights下标
         * 两两等价
         * push(e)	addFirst(e)
         * pop()	removeFirst()
         * peek()	peekFirst()
         */
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;

        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }


        for (int i = 0; i < len; i++) {
            //不remove
            while (!stack.isEmpty() && (heights[i] < heights[stack.peek()])) {
                //确定了当前面积的高度
                int h = heights[stack.peek()];


                while (!stack.isEmpty() && heights[stack.peek()] == h) {
                    stack.pop();
                }
                int w;
                if (stack.isEmpty()) {
                    w = i;
                } else {
                    w = i - stack.peek() - 1;
                }
                res = Math.max(res, h * w);
            }
            stack.push(i);
        }

        //处理还在栈里的柱形高度。右边没有比它高度还小的柱形了，这个时候计算宽度应该假设最右边还有一个下标为 len 的高度为 0 （或者 0.5，只要比 1 小）虚拟柱形。
        if (!stack.isEmpty()) {
            int h = heights[stack.pop()];

            //高度相等的弹出跳过
            while (!stack.isEmpty() && heights[stack.peek()] == h) {
                stack.pop();
            }
            int w;
            if (stack.isEmpty()) {
                w = len;
            } else {
                //宽度不算
                w = len - stack.peek() - 1;
            }
            res = Math.max(res, w * h);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] para = new int[]{2,3};
        System.out.println(largestRectangleArea(para));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
