package com.cxy.leetCode.binary;



/**
 * 针对 有序可能重复 数据集合,找出第一个与给定value相等的元素
 */
public class BinarySearch1 {

    public static int  binarySearch1(int[] a,int value){
       return bsearchInternally1(a,0,a.length-1,value);


    }

    //返回
    public static int  bsearchInternally1(int[] a,int low,int high, int value){
        System.out.println("范围缩至["+low+","+high+"]");
        if(low>high){
            return -1;
        }

        int mid = low+ (high-low)/2;

        if (a[mid]>value){
            return bsearchInternally1(a,low,mid-1,value);
        }else if(a[mid]<value){
            return bsearchInternally1(a,mid+1,high,value);
        }else{
            //a[mid]==value，不确定是不是第一个, mid==0表示是第一个, 除此以外，前一个是不同的值，也表示mid是第一个符合的
            if(mid==0 || a[mid-1]!=value ){
                return mid;
            }else {
                return bsearchInternally1(a,low,mid-1,value);
            }
        }

    }


    public static void main(String args[]){
        int[] a = new int[]{1,3,4,5,6,8,8,11,18,19};
       System.out.println(BinarySearch1.binarySearch1(a,8));
    }


}
