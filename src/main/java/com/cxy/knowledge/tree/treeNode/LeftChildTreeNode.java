package com.cxy.knowledge.tree.treeNode;

import java.util.LinkedList;

/**
 * Description: 左孩子兄弟链表表示法的 节点   <br>
 * Date: 2020/5/9 14:38  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class LeftChildTreeNode<T> {
    //数据
     T data;

     //其他兄弟节点
     LinkedList<LeftChildTreeNode> siblings;

     //左孩子
    LeftChildTreeNode<T> leftChild;
}
