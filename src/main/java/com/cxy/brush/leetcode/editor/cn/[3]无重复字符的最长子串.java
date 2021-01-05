package com.cxy.brush.leetcode.editor.cn;
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 3957 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution3{
    public  int lengthOfLongestSubstring(String s) {

        //corner case
        int len = s.length();
        if(len==0){
            return 0;
        }

       int[] characterFreq = new int[256];
        //init our two pointer
        int start=0;
        int result = 0;
        for(int end=0;end<len;end++){
              characterFreq[s.charAt(end)]++;
              //先加再判断
              while ( characterFreq[s.charAt(end)]>1){
                  characterFreq[s.charAt(start)]--;
                  start++;
            }
            result = Math.max(result,end-start+1);
        }

        return  result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
