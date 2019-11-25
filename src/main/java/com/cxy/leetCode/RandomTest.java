package com.cxy.leetCode;

import java.util.Random;

public class RandomTest {

    public static void main(String args[]){
        int c = 8;
        int a;
        for(int i=1;i<10001;i++){
            Random random = new Random();
            a = random.nextInt();
            if(Void1(a,c )== Void2(a,c)){
                break;
            }
            System.out.println(i);
        }
    }

    public static int Void1(int a,int c){
        if(a==c-1){
            a = 0;
        }else{
            ++a;
        }
        return a;
    }


    public static int Void2(int a,int c){
        return (a+1)%c;
    }
}
