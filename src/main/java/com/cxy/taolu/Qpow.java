package com.cxy.taolu;

/**
 * 快速幂
 */
public class Qpow {

    int mod = 1337;

    /**
     * 快速幂 递归实现
     * @param a
     * @param n
     * @return
     */
    int qpow0(int a, int n) {
        if (n == 0)
            return 1;
        else if (n % 2 == 1)
            return qpow0(a, n - 1) * a;
        else {
            int temp = qpow0(a, n / 2);
            return temp * temp;
        }
    }


    /**
     * 递归快速幂，对大素数取模
     * @return
     */
    long qpow1(int a, int n){

        if (n == 0)
            return 1;
        else if (n % 2 == 1)
            return qpow1(a, n - 1) * a % mod;
        else {
            long temp = qpow1(a, n / 2) % mod;
            return temp * temp  % mod;
        }

    }


    /**
     * 非递归形式 计算 7的10次方 ,n 记为二进制形式 7^(1010)
     * 对于任意底数n，可以拆成若干个相乘
     *
     * 1010的最后一位是0，所以a^1 不要。然后1010变为101，a自乘变为a^2,ans=1
     *
     * 101的最后一位是1，所以a^2要，乘入ans。101变为10，a再自乘变为a^3。
     *
     * 10的最后一位是0，跳过，n右移,a自乘。
     *
     * 1的最后一位是1，ans再乘上a^8。循环结束，返回结果。
     *
     *
     */
    int qpow2(int a, int n){
        int ans = 1;

        while(n>=1){

            //如果n的当前末位为1
            if((n&1) ==1) {
                ans *= a;  //ans乘上当前的a
            }
            //n的当前末位为0
            a *= a;        //a自乘
            n >>= 1;       //n往右移一位
        }
        return ans;
    }


    int qpow(int a, int b) {
        int ans = 1;
        a %= mod;
        while (b != 0) {
            if ((b & 1) != 0) ans = ans * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }



    public static void main(String[] args) {
        new Qpow().qpow2(2,3);
    }


}
