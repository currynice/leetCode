package com.cxy.brush.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
    class Solution239 {
        Deque<Integer> deque = new ArrayDeque<>();

        /**
         * 维护滑动窗口队列
         * @param curr 即将加入的新元素下标
         * @param k    窗口大小
         * @param nums  元素数组
         */
        private void cleanDeque(int curr, int k, int[] nums) {
            // 清除滑动窗口中即将加入的新元素后不再可见的元素
                if(!deque.isEmpty() && deque.peekFirst()==(curr-k)){
                    deque.removeFirst();
                }

        //移除所有比即将加入的新元素小的元素
            while(!deque.isEmpty() && nums[curr] > nums[deque.getLast()]){
                deque.removeLast();
            }
        //现在 队列空 或 队列头是最大的

    }


        public int[] maxSlidingWindow(int[] nums, int k) {

            int len = nums.length;
            //长度 or 窗口size 为0，直接返回空数组
            if(len * k ==0){
                return new  int[0];
            }
            List<Integer> result = new ArrayList<>();

            //随时更新maxIndex;
            int maxIndex = 0;
            //init the  window ,input k times
            for(int i=0;i<k;i++){
                cleanDeque(i,k,nums);
                deque.addLast(i);
                //如果队尾是最大，更新
                if (nums[i] > nums[maxIndex]) maxIndex = i;
            }
            //保存当前的输出结果
            result.add(nums[maxIndex]);

            for(int j=k;j<len;j++){
                cleanDeque(j,k,nums);
                deque.addLast(j);
                //队列头一定是最大的
                result.add(nums[deque.getFirst()]);
            }

            return result.stream().mapToInt(Integer::valueOf).toArray();

        }


    public static void main(String[] args) {

        int[] nums = new int[]{1,3,1,2,0,5};
        Solution239 s = new Solution239();

       System.out.println(Arrays.toString(s.maxSlidingWindow(nums,3)));
    }
    }
//leetcode submit region end(Prohibit modification and deletion)
