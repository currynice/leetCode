package com.cxy.knowledge.sort;


import java.util.Arrays;

//选择排序
public class SelectionSort {
    // a 表示数组，n 表示数组大小

    public static void selectionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; ++i) {
            //找到最小值
            int minIndex = i;
            for(int j=i+1;j<n;++j){
                if(a[j]<a[minIndex]){
                    minIndex = j;
                }
            }
            //交换
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        selectionSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

}
