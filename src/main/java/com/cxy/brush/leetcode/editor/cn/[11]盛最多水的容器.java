package com.cxy.brush.leetcode.editor.cn;
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针 
// 👍 1637 👎 0

/**
 * 算法流程： 设置双指针 i,j 分别位于容器壁两端，根据规则移动指针，并且更新面积最大值 maxArea，直到 i == j (i<right不成立时结束),返回 maxArea。
 *
 * 指针移动规则与证明：
 * 每次更新maxArea时，固定宽度（j-i）,高度选择  h[i]h[i],h[j]h[j] 中的短板，并向中间收窄 1 格。
 *
 * 设每一状态下水槽面积为 S(i, j),0<=i<j<n ，最短的板了决定蓄水量，面积公式 S(i, j) = min(h[i], h[j]) × (j - i)。
 * 无论i板或j板向内收窄 1格，都会导致水槽 底边宽度 -1：
 * 若向内移动短板，但水槽的短板 min(h[i], h[j]) 可能变大，因此水槽面积 S(i, j)可能增大。
 * 若向内移动长板，水槽的短板 min(h[i], h[j]) 不变或变小，下个水槽的面积一定小于当前水槽面积。
 *
 * 因此,必须向内收窄短板可以获取更大的面积。
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution11 {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, maxArea = 0;
        while(i < j){
            //更新maxArea后，向内移动短板
            maxArea = height[i] < height[j] ?
                    Math.max(maxArea, (j - i) * height[i++]):
                    Math.max(maxArea, (j - i) * height[j--]);
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
