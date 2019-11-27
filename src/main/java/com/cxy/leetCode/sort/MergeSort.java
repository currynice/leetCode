package com.cxy.leetCode.sort;

public class MergeSort {

    public static void sort(int[] a){
        int length = a.length;
        recursion(a,length);
    }

    private static void recursion(int[] a,int length){

    }


    /**
     * 递推公式：
     *
     * merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))

     * 终止条件：p >= r 不用再继续分解
     */
}
