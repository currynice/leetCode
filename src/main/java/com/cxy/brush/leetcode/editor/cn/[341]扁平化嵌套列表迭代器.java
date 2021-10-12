package com.cxy.brush.leetcode.editor.cn;

//给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化
//，使之能够遍历这个列表中的所有整数。
//
// 实现扁平迭代器类 NestedIterator ：
//
//
// NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
// int next() 返回嵌套列表的下一个整数。
// boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
//
//
// 你的代码将会用下述伪代码检测：
//
//
//initialize iterator with nestedList
//res = []
//while iterator.hasNext()
//    append iterator.next() to the end of res
//return res
//
// 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
//
//
//
// 示例 1：
//
//
//输入：nestedList = [[1,1],2,[1,1]]
//输出：[1,1,2,1,1]
//解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
//
// 示例 2：
//
//
//输入：nestedList = [1,[4,[6]]]
//输出：[1,4,6]
//解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
//
//
//
//
// 提示：
//
//
// 1 <= nestedList.length <= 500
// 嵌套列表中的整数值在范围 [-106, 106] 内
//
// Related Topics 栈 树 深度优先搜索 设计 队列 迭代器
// 👍 372 👎 0
import com.cxy.brush.leetcode.editor.cn.public_class.NestedInteger;

import java.util.*;
//leetcode submit region begin(Prohibit modification and deletion)



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {

    //data
    private List<Integer> vals;

//  遍历vals的迭代器
    private Iterator<Integer> cur;

//    一.dfs
//    嵌套的整型列表是一个树形结构，叶子节点对应一个整数，非叶节点对应一个列表。
//
//    迭代器遍历的就是对于这棵树进行 dfs 深度优先搜索。
//
//    next & hasNext: 先遍历整个嵌套列表，将所有整数存入一个数组，
//    然后遍历该数组，实现 next 和 hasNext 方法。

//    public NestedIterator(List<NestedInteger> nestedList) {
//        vals = new ArrayList<>();
//        dfs(nestedList);
//        cur = vals.iterator();
//    }
//
//
//    @Override
//    public Integer next() {
//        return cur.next();
//    }
//
//    @Override
//    public boolean hasNext() {
//        return cur.hasNext();
//
//    }


    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                //单独数字加入 vals
                vals.add(nest.getInteger());
            } else {
                //否则 dfs
                dfs(nest.getList());
            }
        }

    }


    /**
     *  但是解法一不符合迭代器的理念，
     *  迭代器不应该对item的值做直接存储，而应提供访问途径：
     *   1.迭代有条件终止(如键值查找)时初始化方法的全局开销非必要.
     *   2.初始化迭代器后，迭代过程中无法处理 List中某NestedInteger值(int值)改变的场景.
     */

//    ---------------------------------------------------------------------------------------------------

    /**
     * 解法二： 用一个栈实现解法一的非递归版本。
     *
     * 栈中存储从根节点到当前节点路径上的所有节点。
     * 因非叶节点对应的是一个列表，所以在栈中存储的是指向列表当前遍历的元素的指针（下标），迭代器（Go中使用切片）。
     * 向下搜索时，取出列表的当前指针指向的元素并将其入栈，同时将该指针向后移动一位，直到找到一个整数。
     * 循环时若栈顶指针指向了列表末尾，则将其从栈顶弹出。
     */

    // 存储nestedList列表的当前遍历位置
    private Deque<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.push(nestedList.iterator());
    }



    @Override
    public Integer next() {
        //测试用例在调用 hasNext 后调用 next(); 因此返回栈顶元素
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()) { // 如果到末尾，出栈
                stack.pop();
                continue;
            }
            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
            NestedInteger nest = it.next();
            if (nest.isInteger()) {
                List<NestedInteger> list = Arrays.asList(nest);
                stack.push(list.iterator());
                //return true 之后，stack的栈顶一定是整数对应指针
                return true;
            }
            stack.push(nest.getList().iterator());
        }
        return false;
    }



}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)
