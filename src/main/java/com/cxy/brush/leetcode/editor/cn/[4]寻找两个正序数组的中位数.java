package com.cxy.brush.leetcode.editor.cn;
//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
// 你可以假设 nums1 和 nums2 不会同时为空。
//
//
//
// 示例 1:
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
//
//
// 示例 2:
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
//
// Related Topics 数组 二分查找 分治算法
// 👍 2906 👎 0

/**
 * 寻找两个有序数组中的第 k 小的数，k 为 (m+n)/2 或 (m+n)/2+1。
 *  找两个数组中第 k (k>1) 小的元素，比较两个指针 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1]
 *          * nums1 中 <=  pivot1 的元素有 nums1[0 .. k/2-2] , k/2-1 个
 *          * nums2 中 <=  pivot2 的元素有 nums2[0 .. k/2-2] , k/2-1 个
 *          *  pivot = min(pivot1, pivot2)，两个数组中所有 <= pivot 的元素不可能超过 (k/2-1)*2 = k-2 个
 *          *  即使pivot 也最多是第 k-1 小的元素，可以不要了
 *          *  pivot = pivot1， nums1[0 .. k/2-1] 都不可能是第 k 小的元素。全部 "删除"，剩下的作为新的 nums1 数组
 *          *  pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
 *          * 由于 "删除" 了一些一定比k小的元素，记得修改 k 的值，减去删除的数的个数
 *
 */


//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //找到目标k
        int numLength = nums1.length + nums2.length;

        if(numLength %2 ==0){
            //元素总数为偶数
            int midIndex1 = numLength / 2 - 1, midIndex2 = numLength / 2;
            return (getKth(nums1,nums2,midIndex1+1) + getKth(nums1,nums2,midIndex2+1))/2.0d;
        }else {
            //元素总数为奇数
            return getKth(nums1,nums2,numLength/2+1)/1.0d;
        }

    }

    /**
     * 获得两个排序数组中第k的元素
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private  int getKth(int[] nums1, int[] nums2, int k) {
//        如果 nums1[k/2 -1 ]  或者 nums2[k/2 -1 ]  越界，需要选取对应数组中的最后一个元素。并且更新k 时不能直接 减去 k/2。

        //定义两个数组的有效起始指针
        int valid1 = 0;
        int valid2 = 0;
        while(true){

            // 边界情况, 一个数组为空，说明该数组中的所有元素都被排除，直接返回另一个数组中第 k 小的元素。
            if (valid1 == nums1.length) {
                return nums2[valid2 + k - 1];
            }
            if (valid2 == nums2.length) {
                return nums1[valid1 + k - 1];
            }
//
//        如果 k=1，返回两个数组有效部分首元素的最小值即可。
        if(1==k){
            return Math.min(nums1[valid1],nums2[valid2]);
        }
         // 正常情况
//            int half = k / 2;
//            int newIndex1 = Math.min(index1 + half, length1) - 1;
//            int newIndex2 = Math.min(index2 + half, length2) - 1;
//            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
//            if (pivot1 <= pivot2) {
//                k -= (newIndex1 - index1 + 1);
//                index1 = newIndex1 + 1;
//            } else {
//                k -= (newIndex2 - index2 + 1);
//                index2 = newIndex2 + 1;
//            }
            //   如果 nums1[valid1+ k/2 -1] 的 nums1[valid1+ k/2 -1] 越界，那么选取对应数组中的最后一个元素。在这种情况下，要根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。
            int newValid1 = Math.min(valid1 +  k / 2, nums1.length) - 1;
            int newValid2 = Math.min(valid2 + k / 2, nums2.length) - 1;
            int pivot1 = nums1[newValid1];
            int pivot2 = nums2[newValid2];

            if(pivot1<=pivot2){
                //删除 nums2[0 .. k/2-1]的元素 以及pivot, 更新有效指针以及k
                k -= (newValid1 - valid1 + 1);
                valid1  = newValid1 +1;
            }else {
                //删除 nums1[0 .. k/2-1]的元素, 更新有效指针以及k
                k -= (newValid2 - valid2 + 1);
                valid2 = newValid2 + 1;
            }
        }

    }


//    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,3};
//        int[] nums2 = new int[]{2};
//        System.out.println(findMedianSortedArrays(nums1,nums2));
//}
}
//leetcode submit region end(Prohibit modification and deletion)
