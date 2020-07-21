package com.cxy.brush.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class So{
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        Deque<Integer> deque = new ArrayDeque<>();

        public void clean_deque(int i, int k,int[] nums) {
            // remove indexes of elements not from sliding window
            if (!deque.isEmpty() && deque.getFirst() == i - k)
                deque.removeFirst();

            // remove from deq indexes of all elements
            // which are smaller than current element nums[i]
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()])
                deque.removeLast();
        }



        public  int[] maxSlidingWindow(int[] nums, int k) {


            int n = nums.length;
            if (n * k == 0) return new int[0];
            if (k == 1) return nums;
            List<Integer> result = new ArrayList<>(n-k+1);

            int max_idx = 0;
            for (int i = 0; i < k; i++) {
                clean_deque(i, k,nums);
                deque.addLast(i);
                // compute max in nums[:k]
                if (nums[i] > nums[max_idx]) max_idx = i;
            }

            result.add(nums[max_idx]);

            // build output
            for (int i  = k; i < n; i++) {
                clean_deque(i, k,nums);
                deque.addLast(i);
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

}


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {



    public  int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) return new int[0];


        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>(len-k+1);


        //初始化双端队列
        for(int i=0;i<k;i++){
            if(deque.isEmpty()){
                deque.addFirst(i);
                continue;
            }
            //初始化时，当前元素大于队列最大值，替换最大值,反正不会溢出deque
            if(deque.peekFirst()!=null && nums[deque.peekFirst()]<nums[i]){
                deque.addFirst(i);
            }else {
                deque.addLast(i);
            }
        }
        result.add(nums[deque.peekFirst()]);


        for(int j=k;j<len;j++){
            if (!deque.isEmpty()){
                //deque 头是看不见的话，移除
                deque.removeFirstOccurrence(j-k);
            }
            //移除所有比当前元素小的deque中下标
            while (!deque.isEmpty() && nums[j] > nums[deque.peekLast()]){
                deque.removeLast();
            }
            deque.addLast(j);
            result.add(nums[deque.peekFirst()]);
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();

    }

}
//leetcode submit region end(Prohibit modification and deletion)
