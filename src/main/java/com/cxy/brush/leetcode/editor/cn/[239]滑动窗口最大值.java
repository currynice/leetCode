package com.cxy.brush.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution239 {
        Deque<Integer> deque = new ArrayDeque<>();

        public void cleanDeque(int curr, int k, int[] nums) {
            // 清除滑动窗口不可见元素
                if(!deque.isEmpty() && deque.peekFirst()==(curr-k))
                deque.removeFirst();



        //移除所有比当前元素小的元素
            while(!deque.isEmpty() && nums[curr] > nums[deque.getLast()])
                deque.removeLast();
        //现在 队列空 或 队列头是最大的

    }


        public int[] maxSlidingWindow(int[] nums, int k) {

            int len = nums.length;
            if(len * k ==0){
                return null;
            }
            List<Integer> result = new ArrayList<>();

            int maxIndex = 0;
            //init
            for(int i=0;i<k;i++){
                cleanDeque(i,k,nums);
                deque.addLast(i);

                if (nums[i] > nums[maxIndex]) maxIndex = i;
            }
            result.add(nums[maxIndex]);

            for(int j=k;j<len;j++){
                cleanDeque(j,k,nums);
                //队列空或队列头一定是最大的
                deque.addLast(j);
                result.add(nums[deque.getFirst()]);
            }

            return result.stream().mapToInt(Integer::valueOf).toArray();

        }

//
//    public static void main(String[] args) {
////        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
//        int[] nums = new int[]{9,10,9,-7,-4,-8,2,-6};
//        maxSlidingWindow(nums,5);
//    }
    }
//leetcode submit region end(Prohibit modification and deletion)
