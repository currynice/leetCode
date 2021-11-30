package com.cxy.brush.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
    class Solution239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindow2(nums,k);
    }



        //双端队列
        Deque<Integer> deque = new ArrayDeque<>();


    /**
     * 解法一： 优先队列
     * 1.先将数组 nums 的前 k 个元素放入优先队列中。向右移动窗口，把一个新的元素放入优先队列中，
     *    堆顶元素就是堆中所有元素的最大值。（然而最大值可能已经不在滑动窗口中，即值在数组中的位置在滑动窗口左边界的左侧）
     *    需要从优先队列中移除。
     *
     * 2. 接着不断地移除堆顶的元素，直到确定其出现在滑动窗口中， 堆顶元素就是当前滑动窗口中的最大值
     *
     * 3.为了方便判断堆顶元素与滑动窗口的位置关系，在优先队列中存储数组 (num,index)，
     *   表示元素 num 在数组中的下标为 index。
     *
     *
     * 时间复杂度:
     *   不好的地方:最坏情况下，数组 nums 中的元素单调递增，
     * 那么最终优先队列中包含了所有元素，没有元素被移除。
     * 因为将一个元素入队优先队列的时间复杂度为 O(logN)，因此最坏时间复杂度为 O(nlogN)。
     *
     * 空间复杂度:
     *   O(n)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;

        // 最大优先队列，nums:{1,3,1,2,0,5}  , [1,0] , [3,1] ,[1,2],[2,3],[0,4],[5,6]
        // override the compare methods(pair1:新入队元素， pair2:旧的top， 值不等时：值大的大； 值相等时：index大的大  )
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1,pair2)->
                pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]
        );

        //init pq , 先入队k 个元素
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        //result
        List<Integer> result = new ArrayList<>();

        result.add(pq.peek()[0]);

        //向后移动
        for (int i = k; i < n; ++i) {
            //加入新元素
            pq.offer(new int[]{nums[i], i});
            //清除不应该存在于 window 的元素
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            result.add(pq.peek()[0]);
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 解法二:单调队列
     * 1.如果当前的滑动窗口中只有下标 i 和 j（i<j），并且i对应元素不大于j对应元素（nums[i]≤nums[j]）
     *    滑动窗口向右移动时，只要i在窗口中， j一定也在窗口中。
     *    因为 nums[i] 不可能是滑动窗口中的最大值了，可以移除。
     *
     * 2. 使用一个队列存储所有未被移除的下标（递增存储），nums中对应的值是递减的。
     *     这种满足这种单调性的双端队列称作「单调队列」
     *
     * 3.滑动窗口向右移动，新元素放入队列。为了保持队列的性质，需要不断将新的元素与队尾元素相比较，
     *    弹出队尾直到队列为空或 新元素值小于队尾值。
     *
     * 4.由于队列中下标对应的元素总是严格单调递减的，因此队首下标对应的元素就是滑动窗口中的最大值。
     *    但需要确保队首元素在窗口中。
     *
     * 5.为了可以同时弹出队首和队尾的元素，使用双端队列 deque。
     *
     * 时间复杂度:
     *   每一个下标恰好仅被放入队列一次，并且最多被弹出队列一次，因此时间复杂度为 O(n)。。
     *
     * 空间复杂度:
     *   O(k): 队列中元素不会超过 k+1 个
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        int len = nums.length;
        //长度 or 窗口size 为0，直接返回空数组
        if(len * k ==0){
            return new  int[0];
        }
        List<Integer> result = new ArrayList<>();


        //init the  window ,first input k times
        for(int i=0;i<k;i++){
            cleanDeque(i,k,nums);
            deque.addLast(i);
        }
        result.add(nums[deque.peek()]);//get head

        for(int j=k;j<len;j++){
            cleanDeque(j,k,nums);
            deque.addLast(j);
            result.add(nums[deque.peek()]);//get head
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();

    }




    /**
     * 维护滑动窗口队列
     * @param curr 即将加入的新元素下标
     * @param k    窗口大小
     * @param nums  元素数组
     */
    private void cleanDeque(int curr, int k, int[] nums) {
        // 清除滑动窗口, 移除那些加入的新元素后，不再可见的元素
        if(!deque.isEmpty() && deque.peekFirst()==(curr-k)){
            deque.removeFirst();
        }

        //移除尾部方向所有比即将加入的新元素小的元素
        while(!deque.isEmpty() && nums[curr] > nums[deque.getLast()]){
            deque.removeLast();
        }
        //维护完毕： 当前队列空 或 队头是最大值

    }


    public static void main(String[] args) {
//
        int[] nums = new int[]{1,3,1,2,0,5};
        Solution239 s = new Solution239();
        System.out.println(Arrays.toString(s.maxSlidingWindow2(nums,3)));
       //should be: [3, 3, 2, 5]

    }
}
//leetcode submit region end(Prohibit modification and deletion)
