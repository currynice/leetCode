package com.cxy.knowledge.sort;

import java.util.Arrays;

public class QuickSort {


    public static void quickSort(int[] arr){
        int length = arr.length;
        quickSortInternally(arr,0,length-1);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 快排递归函数
     * quick_sort(p…r) =quick_sort(p…q)+quick_sort(q+1…r)
     * 终止条件:
     * p>=r, 不再分区
     *
     * 在数组a[p,r]中，选定一个pivot(这里选最后一个),
     * @param a
     * @param p
     * @param r
     */
    private static void quickSortInternally(int[] a,int p,int r){
        if(p>=r){
            return;
        }

        //获得分区点
        int q = partition(a,p,r);
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);

    }


    //原地分区函数,返回分区点下标
    private static int partition(int[] a,int p,int r){
        //这里分区点pivot选择最后一个元素
            int pivot = a[r];

            //分区点指针
            int i = p;

            //利用指针j遍历,从a[p..r-1]取一个元素a[j],
            for(int j=p;j<r;j++) {
                if (a[j] < pivot) {
                    if (i == j) {
                        i++;
                    } else {
                        //交换a[i],a[j],分区点指针前进
                        int tem = a[i];
                        a[i++] = a[j];
                        a[j] = tem;
                    }
                }
            }
            //交换a[i],a[r]
            int tem = a[i];
            a[i] =a[r];
            a[r] = tem;
            return i;
    }


    public static void main(String args[]){
        int[] a = new  int[]{6,11,3,9,8};

        quickSort(a);
    }


}
