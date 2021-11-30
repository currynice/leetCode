package com.cxy.brush.leetcode.editor.cn.public_class;


import java.util.List;


/**
 * Description:   </br>
 * Date: 2021/10/12 15:26
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public interface NestedInteger {

     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}
