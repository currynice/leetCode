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
 *  1: 当总元素数 是偶数（such as 10 ），中位数取两有序数组 第5 大 和 第 6 大的元素的平均值
 *     当总元素数 是奇数时（such as 9），中位数取 两有序数组 第5 大 的元素
 *
 *
 *  other:
 *  2: 归并合并两个有序数组，得到一个大的有序数组。中位数取中间位置元素。
 *
 *  3:不合并，只要找到中位数的位置即可。维护两个指针，初始时分别指向两个数组的下标 0的位置，
 *     每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，不再移动，仅移动另一个数组的指针），直到到达中位数的位置。
 *
 */


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    //way1
//    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int numLength = nums1.length + nums2.length;
//        if(numLength %2 ==0){
//            //元素总数为偶数 , 需要两个元素计算得出(如10个元素，中位数是第5小的元素 和 第6小的元素 的平均值)
//            return (getKth(nums1,nums2,numLength / 2) + getKth(nums1,nums2,numLength / 2+1))/2.0d;
//        }else {
//            //元素总数为奇数,k =(m+n)/2+1 ,k是真实存在的元素(如11个元素，第6小的元素即为中位数)
//            return getKth(nums1,nums2,numLength/2+1)/1.0d;
//        }
//    }


    //way2
    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int numLength = nums1.length + nums2.length;
        if(numLength %2 ==0){
            //元素总数为偶数 , 需要两个元素计算得出(如10个元素，中位数是第5小的元素 和 第6小的元素 的平均值)
            return (getKthSlow(nums1,nums2,numLength / 2) + getKthSlow(nums1,nums2,numLength / 2+1))/2.0d;
        }else {
            //元素总数为奇数,k =(m+n)/2+1 ,k是真实存在的元素(如11个元素，第6小的元素即为中位数)
            return getKthSlow(nums1,nums2,numLength/2+1)/1.0d;
        }

    }

    /**
     * 获得两个有序数组中 第 k大的元素
     *  直接比较 v1=nums1[k/2 -1]  和 v2=nums2[k/2 -1]，类似二分法
     *         因为:
     *            nums1 中 <=  v1 的元素有: nums1[0], ... ,nums1[k/2-2], 共 k/2-1 个
     *            nums2 中 <=  v2 的元素有: nums2[0], ..., nums2[k/2-2] ,共 k/2-1 个
     *            对于v3=min(v1,v2), 两个数组中所有 <= v3 的元素最多 (k/2-1)*2 = k-2 个
     *            1)如果v3=v1(v1<v2): nums1中下标 0 - k/2-1 的元素都不可能是第 k 小的元素。全部可以 "舍弃"不看
     *            2)如果v3=v2(v1>v2): nums2中下标 0 - k/2-1 的元素都不可能是第 k 小的元素。全部可以 "舍弃"不看
     *            3)如果v1=v2: 可以归入第一种情况
     *
     *           因为"舍弃"了x个元素，新目标从 第k小变为第 k-x 小
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */

    private  int getKth(int[] nums1, int[] nums2, int k){

        //两个数组有效部分 第 target 个元素
        int target = k;

        int valid1 = 0,valid2 = 0;
        while(true){

            //corner case ，当某一个数组已经全部舍弃不看，在另一个数组的有效部分返回第k大的元素。
            if(valid1 == nums1.length){
                return nums2[valid2+ target-1];
            }

            if(valid2 == nums2.length){
                return nums1[valid1 + target-1];
            }

            // 如果 target = 1，返回两个数组有效部分第一个元素(index=valid_ )中的更小值。
            if(target==1){
                return Math.min(nums1[valid1],nums2[valid2]);
            }


            //比较 v1=nums1[k/2 -1]  和 v2=nums2[k/2 -1] ，考虑数组越界
            int compare1 = Math.min(valid1 + target/2 - 1, nums1.length-1);
            int compare2 = Math.min(valid2 + target/2 - 1, nums2.length-1);

            int v1 = nums1[compare1];

            int v2 = nums2[compare2];

            //当 v1 <= v2 , num1 的compare1 以及之前的元素可以舍弃不看
            if(v1 <=v2){
                //更新target ，因为舍弃了 compare1 -valid1 +1 个元素
                target -= (compare1 - valid1+1);
                //舍弃
                valid1 = compare1+1;
            }else {
                //当 v1 >v2 ，nums2 的compare2 以及之前的元素可以舍弃不看
                //更新target，因为舍弃了 compare2 - valid2 +1 个元素
                target -= (compare2 - valid2+1);
                //舍弃
                valid2 = compare2+1;
            }

        }
    }


    /**
     * 一步一步地 移动，比较慢的找 两个有序数组第 k 大 的元素
     * 维护两个指针，初始时分别指向两个数组的下标 0的位置，
     * 每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，不再移动，仅移动另一个数组的指针），直到到达中位数的位置。
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private  int getKthSlow(int[] nums1, int[] nums2, int k){
        int pointer1 = 0;
        int pointer2 = 0;

        //要操作 k-1次 后，返回最小的
        int step = k-1;

        if(nums1.length==0){
            System.out.println("两个数组第"+k+"小的元素是:"+nums2[k-1]);

            return nums2[k-1];
        }

        if(nums2.length==0){
            System.out.println("两个数组第"+k+"小的元素是:"+nums1[k-1]);

            return nums1[k-1];
        }


        for(int i=0;i<step;i++){

            if(pointer1  == nums1.length && pointer2<nums2.length){
                    pointer2++;
                    break;
            }else if(pointer2 == nums2.length && pointer1<nums1.length){
                pointer1++;
                break;
            }else {
            int v1 = nums1[Math.min(pointer1, nums1.length-1)];
            int v2 = nums2[Math.min(pointer2, nums2.length-1)];
            //移除较小的
            if(v1<=v2){
                pointer1++;
            }else {
                pointer2++;
            }
            }
        }

        if(pointer1 == nums1.length){
            System.out.println("两个数组第"+k+"小的元素是:"+nums2[pointer2]);

            return nums2[pointer2];
        }

        if(pointer2 == nums2.length){
            System.out.println("两个数组第"+k+"小的元素是:"+nums1[pointer1]);
            return nums1[pointer1];
        }

        int result =  Math.min(nums1[pointer1],nums2[pointer2]);
        System.out.println("两个数组第"+k+"小的元素是:"+result);
        return result;
    }








    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3,4,5,6};
        Solution s = new Solution();
//        System.out.println(s.findMedianSortedArrays(nums1,nums2));
        s.getKthSlow(nums1,nums2,3);
        s.getKthSlow(nums1,nums2,4);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
