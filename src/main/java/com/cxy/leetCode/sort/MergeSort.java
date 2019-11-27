package com.cxy.leetCode.sort;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] a){
        int length = a.length;
        mergeSortInternally(a,0,length-1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 递推公式：
     * merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))
     * 终止条件：p >= r 不用再继续分解
     */
    private static void mergeSortInternally(int[] a,int p,int r){
        //终止分解
        if(p >= r){
            return;
          }

        //取p到r的中间位置,防止(p+r)/2出现溢出
        int q = p+ ((r-p)/2);
        //分治
        mergeSortInternally(a,p,q);
        mergeSortInternally(a,q+1,r);

        //归并p-q ,q+1-r的数据
        mergeWithSenty(a,p,q,r);

    }

    //将两个有序数组(A[p , q] ,A[q+1 , r])合并成一个有序数组
    // 1.用两个游标 i 和 j，分别指向 A[p…q] 和 A[q+1…r] 的第一个元素。
    // 2.申请一个临时数组 tmp，容量与与 A[p…r]匹配。
    // 3.比较这两个元素 A[i] 和 A[j]，将符合排序规则的元素(这里是更小的)放入到临时数组 tmp，并且该数组元素对应游标后移一位。
    // 4.结束条件:直到其中一个子数组元素全部移动到tmp，把另一个数组sorted的元素加入到tmp的末尾。
    // 5.最后再把临时数组 tmp 中的数据拷贝到原数组 A[p…r] 中
    private static void merge(int[] a, int p, int q, int r) {

        // 用两个游标 i 和 j，分别指向 A[p…q] 和 A[q+1…r] 的第一个元素。
        int i = p;
        int j = q+1;
        //tmp初始下标
        int k = 0;

        // 申请一个临时数组 tmp，容量与与 A[p…r]匹配。
        int[] tmp = new int[r-p+1];
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; //先取值再自增
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 子数组[p,q]中有剩余的数据
        int start = i;
        int end = q;
        // 子数组[q+1,r]中有剩余的数据
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }


        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }



    // 利用哨兵模式, 将两个有序数组(A1[p , q] ,A2[q+1 , r])合并成一个有序数组
    // 申请两个数组leftArr,rightArr,容量比A1,A1 大1个容量空间
    // 拷贝A1中元素到leftArr，最后一位加上MAX_VALUE,作为哨兵，方便比较
    // 拷贝A2中元素到rightArr，最后一位加上MAX_VALUE,作为哨兵，方便比较
    // 访问r-p+1次(两个数组总容量数)
    // 比较leftArr,rightArr的每一位元素, 将符合排序规则的元素(这里是更小的)覆盖数组a，并且该数组对应游标后移一位。
    // 结束条件:当一个数组访问到哨兵值时，对应游标不再增加，直到另一数组访问完剩余值
    private static void mergeWithSenty(int[] a, int p, int q, int r) {

        int[] leftArr = new int[q - p +2];//q - (p-1)+1:容量比A1多一个
        int[] rightArr = new int[r - q +1];//r - q+1:容量比A2多一个

        for (int i = 0; i <= q - p; i++) {
            leftArr[i] = a[p + i];
        }
        // leftArr末尾添加哨兵
        leftArr[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - q; i++) {
            rightArr[i] = a[q + 1 + i];
        }
        // rightArr末尾添加哨兵
        rightArr[r-q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            // 当一个数组访问到哨兵值时，对应游标不再增加，直到另一数组访问完剩余值
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }
    }

    public static void main(String args[]){
        int[] a = new  int[]{11,8,3,9,7,1,2,5};

        mergeSort(a);
    }

}
