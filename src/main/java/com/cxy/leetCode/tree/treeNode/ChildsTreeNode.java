package com.cxy.leetCode.tree.treeNode;


import java.util.ArrayList;

/**
 * Description: 孩子数组表示法的 节点   <br>
 * Date: 2020/5/9 14:38  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class ChildsTreeNode<T> {
    //数据
     T data;

     //孩子节点
    ArrayList<ChildsTreeNode<T>> childs;
}
