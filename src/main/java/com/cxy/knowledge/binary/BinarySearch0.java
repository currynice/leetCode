package com.cxy.knowledge.binary;


/**
 * 仅针对 有序不重复 数据集合,找出与给定value相等的数据
 * 参考Arrays工具类binarySearch()
 */
public class BinarySearch0 {

    public static int  binarySearch(int[] a,int value){
       return bsearchInternally(a,0,a.length-1,value);


    }

    public static int  bsearchInternally(int[] a,int low,int high, int value){
        System.out.println("进行一次查找");
        if(low>high){
            return -1;
        }

        int mid = low+ (high-low)/2;

        if(a[mid]==value){
            return value;
        }else if (a[mid]>value){
            return bsearchInternally(a,low,mid-1,value);
        }else {
            return bsearchInternally(a,mid+1,high,value);
        }

    }


    public static void main(String args[]){
        int[] a = new int[]{8,11,19,23,27,33,45,55,67,98};
       System.out.println(BinarySearch0.binarySearch(a,19));
    }


}
