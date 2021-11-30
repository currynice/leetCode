package com.cxy.brush.leetcode.editor.cn;
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针 
// 👍 562 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {


        //从各自有效部分的末尾开始
        int i = m-1;
        int j = n-1;
        //要填数 k次
        for(int k =nums1.length-1; k>=0;k--){

            /**
             * j 到头了（小于0），只能考虑放i
             * i，j都没到头，正常考虑大小，较大的放在k位置上
             * j没到头，i到头，只能放j
             */
            if(j<0 || (i>=0&&nums1[i]>=nums2[j])){
                nums1[k] = nums1[i];
                i--;
            }else {
                nums1[k] = nums2[j];
                j--;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
