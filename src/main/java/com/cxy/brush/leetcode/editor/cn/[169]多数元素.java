package com.cxy.brush.leetcode.editor.cn;//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 位运算 数组 分治算法 
// 👍 830 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution169 {

    /**
     * Map 法
     * @param nums
     * @return
     */


    /**
     * 摩尔投票法
     * 初始化 候选人(cand_num)为 nums[0]，票数count初始化为1。
     *
     * 遍历:
     * 当遇到与cand_num相同的数，票数count = count + 1，否则票数count = count - 1。
     * 当票数count为0时，更换候选人，并将票数count重置为1。
     * 遍历完数组后的cand_num即为最终答案。
     *
     * why？
     * 遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 因为“众数”个数 >  n/2 ，其余元素的个数必然 <=  n/2 。
     * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        int can_num = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++){
            if(count==0){
                can_num = nums[i];
                count =1;
                continue;
            }

            if(nums[i]==can_num){
                count = count+1;
            }else {
                count = count-1;
            }
        }

        return can_num;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
