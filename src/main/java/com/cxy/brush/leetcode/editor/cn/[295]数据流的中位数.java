package com.cxy.brush.leetcode.editor.cn;

//数据流的中位数
// 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 堆 设计 
//

import java.util.PriorityQueue;

/**
 * 解析:
 *     如果是固定的数据数组，先排序，再直接根据数组长度访问中位数index
 *
 * 数据结构选择：
 * 一.数组
 *     因为数据规模大，每次排序虽然可以用二分法插入，但移动插入位置后序元素的时间复杂度很高
 *
 * 二.链表
 *     插入的成本降低了，但每次维护排序需要线性遍历出插入位置[O(N）], 且得出中位数的时间复杂度也只能遍历[O（N）]
 *
 *  三.平衡二叉树
 *     增删查改复杂度都是 O(logN)，看起来OK, Jdk的 TreeSet底层是红黑树，addNum直接插入，findMedian可以通过返回元素个数api 推出计算中位数的元素的排名。
 *
 *     问题1：TreeSet是Set啊，元素不能重复的，但数据流是可能出现重复数据的。
 *
 *     问题2：TreeSet 还没有通过排名快速计算元素的 API（类似rank() ）。既直接返回TreeSet中第 5 大的元素。
 *
 *
 *  四.优先级队列（二叉堆） PriorityQueue
 *     虽然维护了内部元素动态有序，好像也不行，因为优先级队列是操作受限的数据结构，只能从堆顶添加/弹出元素，但中位数是要在排序中间位置取出元素的。
 *
 *
 *  最终的骚操作: 两个优先级队列
 *      中位数 是通过对有序元素集合最中间或最中间两个元素算出来的，
 *      一个从小到大的有序数组（1，3，3，5，7，9，9，10，13）可以由两个子数组来替代：
 *      一个从小到大的有序数组a （1，3，3，5，7） 和一个由大到小的有序数组b（13，10，9，9） ，中位数通过a的最大元素和b的最小元素算出，分别用两个优先级队列表示这两个数组
 *      a,b的元素个数等于或最多相差1个 ,但b中元素都大于a中元素
 *
 * */
//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {


    private PriorityQueue<Integer> large;
    private PriorityQueue<Integer> small;

    /** initialize your data structure here. */
    public MedianFinder() {
        //小顶堆，元素小
        large = new PriorityQueue<>();
        // 大顶堆，元素大
        small = new PriorityQueue<>((a, b) -> {
            //从大到小
            return b - a;
        });
    }
    
    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }
    
    public double findMedian() {
        //个数不一样，返回元素个数多的堆的顶
         if(large.size()<small.size()){
             return small.peek();
         }else  if(large.size()>small.size()){
             return large.peek();
         }
             return(large.peek() + small.peek()) / 2.0;
         }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
