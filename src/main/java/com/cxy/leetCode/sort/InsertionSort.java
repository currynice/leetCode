package com.cxy.leetCode.sort;

import java.util.Arrays;

public class InsertionSort {
    // 插入排序，a 表示数组，n 表示数组大小

    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;
        //第一个为初始一排序区间，遍历n-1次
        for (int i = 1; i < n; ++i) {
            System.out.println("第"+i+"次排序");
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            //j  可能是-1
            a[j+1] = value; // 插入数据
        }


        System.out.println(Arrays.toString(a));
    }



    public static void main(String args[]){
        //4，5，6，3，2，1 要冒泡排6次
        int[] arr1 = new int[]{4,5,6,1,3,2};
        insertionSort(arr1,6);

    }


}
