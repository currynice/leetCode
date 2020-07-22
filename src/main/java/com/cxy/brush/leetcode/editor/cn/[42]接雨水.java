package com.cxy.brush.leetcode.editor.cn;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针 
// 👍 1484 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution42 {

    //O(n^2)
    public int trap0(int[] height) {
        int result = 0;

        //两端都存不了水
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出当前列左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //取相对较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，小于等于该列都存不了水
            if (min > height[i]) {
                result  += (min - height[i]);
            }
        }
        return result;

    }

    //O(n)
    public  int trap2(int[] height) {
        int result = 0;

        //index列  左边墙最大高度数组
        int[] maxLeft = new int[height.length];

        //index列  右边墙最大高度数组
        int[] maxRight = new int[height.length];

        //两端都存不了水
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(height[i - 1], maxLeft[i - 1]);
        }

        for (int i = height.length - 2; i >=0 ; i--) {
            maxRight[i] = Math.max(height[i+1], maxRight[i +1]);

        }


        //取相对较小的
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i],  maxRight[i]);

            //只有较小的一段大于当前列的高度才会有水，小于等于该列都存不了水
            if (min > height[i]) {
                result  += (min - height[i]);
            }

        }
        return result;
    }

    public int trap(int[] height) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        //遍历一遍
        while (current < height.length) {
            //如果栈不空且当前高度大于栈顶高度
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) {
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                result = result + distance * (min - h);
            }
            //栈为 空 或 当前高度小于等于栈顶元素,不计算
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return result;
    }

    /**
     *
     * 遍历每一列墙，每次匹配出对应的一堵墙，计算这两堵墙中的水。
     *
     * 用栈保存每列墙，初始length[0]入栈,curr从index:0出发
     *
     * 如果当前高度小于栈顶高度，说明这里会有积水，我们将墙的高度的下标入栈。
     *
     *             如果当前高度大于栈顶高度，说明可以计算之前的积水。计算完，先清空栈，再把当前的墙继续入栈，作为新的积水的墙。
     *
     *
     *
     * 当前高度 length[curr] 小于等于栈顶高度 stack.peek()，入栈，curr指针后移。
     *
     * 当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。
     * 直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
     *
     * 我们看具体的例子。
     *
     * 首先将 height [ 0 ] 入栈。然后 curr 指向的高度大于栈顶高度，所以把栈顶 height [ 0 ] 出栈，然后栈空了，再把 height [ 1 ] 入栈。current 后移。

     */

//    public static void main(String[] args) {
//        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
//
//        trap(height);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
