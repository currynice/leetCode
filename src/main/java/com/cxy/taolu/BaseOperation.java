package com.cxy.taolu;

/**
 * Description:  数据结构基本增,删,改,查套路框架
 *
 * 一.遍历：
 *    1.1线性数据结构：迭代
 *    1.2非线性数据结构：递归
 *
 * <br>
 * Date: 2020/7/14 17:01  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class BaseOperation {

    /**
     * 迭代遍历： for 循环遍历,  for each或Iterator迭代器遍历
     * 数组为代表的线性表进行迭代遍历
     * @param arr
     */
    void traverse(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    class ListNode{
        int val;
        ListNode next;
    }

    /**
     *  遍历一个链表，既可以迭代遍历，也可以递归
     * @param head
     */
    void traverseList(ListNode head){
        for(ListNode p=head;p!=null;p = p.next){
            System.out.println("迭代遍历ListNode"+p.val);
        }
    }

    /**
     * 也可以递归,可以说兼具了迭代和递归，但是，线性表和非线性表分的那么清楚干嘛呢
     * @param head
     */
    void traverseList2(ListNode head){
        System.out.println("递归遍历ListNode"+head.val);
        traverseList2(head.next);
    }


    //二叉树，这是典型的非线性表,递归
    class BinaryTreeNode{
        int val;
        BinaryTreeNode left,right;//左右孩子
    }

    void traverseBinaryTree(BinaryTreeNode root){
        traverseBinaryTree(root.left);
        traverseBinaryTree(root.right);
    }


    //多叉树
    class MultiTreeNode{
        int val;
        MultiTreeNode[] childrens;//孩子节点
    }



    void traverseMultiTree(MultiTreeNode root){
        for(MultiTreeNode child:root.childrens){
            traverseMultiTree(child);
        }
    }



}
