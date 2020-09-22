package com.cxy.knowledge.binary;


/**
 * 针对 有序可能重复 数据集合,找出最后一个小于等于给定value的元素
 */
public class BinarySearch4 {

    public static int  binarySearch4(int[] a,int value){
        return bsearchInternally4(a,0,a.length-1,value);


    }

    //返回
    public static int  bsearchInternally4(int[] a,int low,int high, int value){
        System.out.println("范围缩至["+low+","+high+"]");
        if(low>high){
            return -1;
        }

        int mid = low+ (high-low)/2;

        if(a[mid]>value){
            return bsearchInternally4(a,low,mid-1,value);
        }else{
            //a[mid]<=value，不确定是不是最后一个, mid==a.length-1表示是最后一个, 除此以外，mid后一个元素大于value的话，也表示mid是最后一个符合的
            if(mid==a.length-1 || a[mid+1]>value ){
                return mid;
            }else {
                return bsearchInternally4(a,mid+1,high,value);
            }
        }

    }


    public static void main(String args[]){
        int[] a = new int[]{1,3,4,5,6,8,8,11,18,19};
        System.out.println(BinarySearch4.binarySearch4(a,14));
    }


}

