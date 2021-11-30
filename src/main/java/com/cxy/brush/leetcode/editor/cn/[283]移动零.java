package com.cxy.brush.leetcode.editor.cn;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 649 👎 0


import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution283 {

    public static void moveZeroes(int[] nums) {
        // n 之前的元素都是非0
        int n=0;
        for(int i=0;i<nums.length;i++){
            //留下非0
            if(nums[i]!=0){
                //把非0的数 移给n
                nums[n]=nums[i];
                n++;
            }
        }
        //补0
        while(n < nums.length){
            nums[n] =0;
            n++;
        }

    }










        public static void moveZeroes2(int[] nums) {
        //双指针i,j
        int j = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                if(i!=j){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
                j++;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
       moveZeroes(nums);
       System.out.println();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
