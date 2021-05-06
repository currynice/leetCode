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
 *
 *  other:
 *  2:使用归并的方式，合并两个有序数组，得到一个大的有序数组。中位数取大的有序数组的中间位置元素。
 *
 *  3:不需要合并，只要找到中位数的位置即可。维护两个指针，初始时分别指向两个数组的下标 0的位置，
 *     每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，只移动另一个数组的指针），直到到达中位数的位置。
 *
 */


//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //目标，两数组的中位数
        int numLength = nums1.length + nums2.length;

        if(numLength %2 ==0){
            //元素总数为偶数 , 需要两个元素计算得出(如10个元素，中位数是第5小的元素 和 第6小的元素 的平均值)
            return (getKth(nums1,nums2,numLength / 2) + getKth(nums1,nums2,numLength / 2+1))/2.0d;
        }else {
            //元素总数为奇数,k =(m+n)/2+1 ,k是真实存在的元素(如11个元素，第6小的元素即为中位数)
            return getKth(nums1,nums2,numLength/2+1)/1.0d;
        }

    }

    /**
     * 获得两个有序数组中 第 k大的元素
     *  直接比较 v1=nums1[k/2 -1]  和 v2=nums2[k/2 -1]，类似二分法
     *         因为:
     *            nums1 中 <=  v1 的元素是: nums1[0], ... ,nums1[k/2-2], 共 k/2-1 个
     *            nums2 中 <=  v2 的元素是: nums2[0], ..., nums2[k/2-2] ,共 k/2-1 个
     *            对于v3=min(v1,v2), 两个数组中所有 <= v3 的元素不可能超过 (k/2-1)*2 = k-2 个
     *            1)如果v3=v1(v1<v2): nums1中下标 0 - k/2-1 的元素都不可能是第 k 小的元素。全部可以 "舍弃"不看
     *            2)如果v3=v2(v1>v2): nums2中下标 0 - k/2-1 的元素都不可能是第 k 小的元素。全部可以 "舍弃"不看
     *            3)如果v1=v2: 那么归入第一种情况
     *           "舍弃"了x个元素，则目标从第k小变为第k-x小
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private  int getKth(int[] nums1, int[] nums2, int k) {

        //定义两个数组 有效部分（未舍弃部分）起始指针
        int valid1 = 0;
        int valid2 = 0;
        while(true){

            // corner case: 任一数组为空，直接返回另一个数组中第 k 小(index为 valid_+ k-1)的元素。
            if (valid1 == nums1.length) {
                return nums2[valid2 + k - 1];
            }
            if (valid2 == nums2.length) {
                return nums1[valid1 + k - 1];
            }

        //  如果 k=1，返回两个数组有效部分第一个元素(index=valid_ )中的更小值。
           if(1==k){
              return Math.min(nums1[valid1],nums2[valid2]);
           }

            //如果 nums1[valid1+ k/2 -1] 或 nums2[valid2+ k/2 -1] 越界了
            // 那么选取对应数组中的最后一个元素。作为比较数v1/v2
            int newValid1 = Math.min(valid1 + k/2 - 1, nums1.length-1);
            int newValid2 = Math.min(valid2 + k/2 - 1, nums2.length-1);
            int v1 = nums1[newValid1];
            int v2 = nums2[newValid2];

            if(v1<=v2){
                //nums1中舍弃了一些元素，更新目标k, 更新nums1的有效指针valid1
                k -= (newValid1 - valid1 + 1);
                valid1  = newValid1 +1;
            }else {
                //nums2中舍弃了一些元素，更新目标k, 更新nums2的有效指针valid2
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
