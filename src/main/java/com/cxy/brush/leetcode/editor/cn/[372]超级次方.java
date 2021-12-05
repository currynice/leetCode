package com.cxy.brush.leetcode.editor.cn;//你的任务是计算 aᵇ 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
//
// 
//
// 示例 1： 
//
// 
//输入：a = 2, b = [3]
//输出：8
// 
//
// 示例 2： 
//
// 
//输入：a = 2, b = [1,0]
//输出：1024
// 
//
// 示例 3： 
//
// 
//输入：a = 1, b = [4,3,3,8,5,2]
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：a = 2147483647, b = [2,0,0]
//输出：1198
// 
//
// 
//
// 提示： 
//
// 
// 1 <= a <= 2³¹ - 1 
// 1 <= b.length <= 2000 
// 0 <= b[i] <= 9 
// b 不含前导 0 
// 
// Related Topics 数学 分治 👍 157 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution372 {

    int mod = 1337;


    //a^k=a^[(k/10*10)+k mod 10] = a^ (k/10*10)  *  a^(k mod 10) = (a^ (k/10))^1010  *  a^(k mod 10)
    //当a=99, k=2345 , 99^2345 = 99^(234*10 +5) =  99^(234*10) * 99^5 = (99^234)^10 * 99^5
    //最终k可以转换为10以内的数
    public int superPow(int a, int[] b) {
        //从末位开始
            return dfs(a,b,b.length-1);
    }

    /**
     *  k=b[i]， 计算 a的k次幂 mod 1337 的结果
     * @param a
     * @param b
     * @param i
     * @return
     */
    private int dfs(int a, int[] b, int i) {
        if(i==-1){
            return 1;
        }
        return (int)(qpow2(dfs(a,b,i-1),10) * qpow2(a,b[i]) % mod);
    }


    /**
     * 递归快速幂，对大素数取模
     * @return
     */
    long qpow2(long a, long n){

        if (n == 0)
            return 1;
        else if (n % 2 == 1)
            return qpow2(a, n - 1) * a % mod;
        else {
            long temp = qpow2(a, n / 2) % mod;
            return temp * temp  % mod;
        }

    }

    int qpow1(int a, int b) {
        int ans = 1;
        a %= mod;
        while (b != 0) {
            if ((b & 1) != 0) ans = ans * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }



//    public static void main(String[] args) {
//
//        System.out.println(new Solution().qpow1(2147483647, 200));
//        System.out.println(new Solution().qpow2(2147483647, 200));
//
//    }


}
//leetcode submit region end(Prohibit modification and deletion)
