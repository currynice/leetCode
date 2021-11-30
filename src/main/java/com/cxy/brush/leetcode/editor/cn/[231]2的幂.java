package com.cxy.brush.leetcode.editor.cn;
//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 273 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution231 {

    /**
     * 2的幂次方，二进制位仅有一个1，且不是末位  0....1..0
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        long x = n;
          return x!=0 && ((x&(x-1))==0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
