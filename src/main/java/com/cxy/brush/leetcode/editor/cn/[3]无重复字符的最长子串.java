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


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution3{


    /**
     * start指针枚举每一个字符
     * 使用两个指针表示某个子串（窗口）的左右边界，start左指针代表着「子串的起始位置」，end右指针即该无重复子串的终结位置
     *
     * 每一步，将左指针向右移动一格，表示要将下一个字符作为起始位置了，
     *        然后不断地向右移动右指针，始终保证窗口子串中没有重复的字符。
     *        在移动结束后，根据这个子串的长度，更新结果
     *
     * 枚举结束后，返回最长的子串的长度
     *
     *
     * @param s
     * @return
     */
    public  int lengthOfLongestSubstring(String s) {

        //corner case
        if(s==null || s.length()==0){
            return 0;
        }

        int length = s.length();


        //最终的结果
        int result = 0;

        //左指针
        int start = 0;

        //右指针
        int end = -1;

        //保证 start,end 维护的窗口代表的字符串是不重复的

        // set 记录窗口对应 字符串 的字符，add 前用 contains 判断下，start 指针移动都需要及时清理
        Set<Character> occ = new HashSet<>();

        for(start=0;start<length;start++) {
            if (start > 0) {
                // 左指针移动了
                // 维护occ ,清除不在窗口的字符对应记录
                occ.remove(s.charAt(start - 1));
            }
            //试探end向右移的话，滑动窗口是否还满足条件(end试探位置的字符不在occ中，继续右移动)
            //end 的移动范围 [start,length -1]
            while( end<length-1 && !occ.contains(s.charAt(end+1))){
                occ.add(s.charAt(end+1));
                //这时才真正右移 end
                end++;
            }
            result = Math.max(result,end-start+1);
        }

        return result;
    }





    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
    }





}
//leetcode submit region end(Prohibit modification and deletion)
