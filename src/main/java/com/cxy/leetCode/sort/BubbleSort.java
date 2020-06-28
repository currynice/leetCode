package com.cxy.leetCode.sort;

import java.util.Arrays;

/**
 * 冒泡排序（比较相邻两个元素）,没有元素交换就提前退出
 */
public class BubbleSort {

    //数据放在数组里,由小到大排序
    public static void sort(int[] a){
        int n = a.length;

        if (n <= 1) return;

        //排序n次
        for(int i=0;i<n;i++){
            System.out.println("排序第"+(i+1)+"次");
            //数据交换标志
            boolean flag = false;
            for(int j=0;j<n-i-1;j++){
                //比较
                if(a[j]>a[j+1]){
                    //exchange way 1:
//                    int temp = a[j];
//                    a[j] = a[j+1];
//                    a[j+1] = temp;
                    //exchange way 2 异或:
                    a[j] = (a[j] ^ a[j+1]);
                    a[j+1] =  (a[j] ^a[j+1]);
                    a[j] = (a[j] ^ a[j+1]);
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
        System.out.println(Arrays.toString(a));

    }


    //每一次冒泡后记录最后一次元素交换的位置,作为下次比较的边界,
    // 对于边界外的元素在下次循环中无需比较.
    public static void sort2(int[] a){

        int n = a.length;

        if (n <= 1) return;

        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界,每次只需要比较到这里即可退出
        int sortBorder = n - 1;

        //排序n次
        for(int i=0;i<n;i++){
            System.out.println("排序第"+(i+1)+"次");
            //数据交换标志
            boolean flag = false;
            for(int j=0;j<sortBorder;j++){
                //比较
                if(a[j]>a[j+1]){
                    //exchange1:
//                    int temp = a[j];
//                    a[j] = a[j+1];
//                    a[j+1] = temp;
                    //exchange2:
                    a[j] = (a[j] ^ a[j+1]);
                    a[j+1] =  (a[j] ^a[j+1]);
                    a[j] = (a[j] ^ a[j+1]);
                    flag = true;
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag)break;
        }
        System.out.println(Arrays.toString(a));

    }

    /**
     *
     * 利用异或特性进行交换
     * 两个相等的数疑异或为0
     * 一个数和0异或后不变
     * @param a
     * @param b
     * @return  [b,a]
     */
    public static int[] exchanged(int a,int b){
        a = (a ^ b);
        b =  (a ^ b);
        a = (a ^ b);
        return new int[]{a,b};
    }

    public static void main(String args[]){
        //4，5，6，3，2，1 要冒泡排6次
        int[] arr1 = new int[]{4,5,6,3,2,1};
        sort2(arr1);

        //3,5,4,1,2,6 要冒泡排4次
        sort2(new int[]{3,5,4,1,2,6});
    }
}
