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
//    public  int lengthOfLongestSubstring(String s) {
//        //corner case
//        if(s==null || s.length()==0){
//            return 0;
//        }
//        int length = s.length();
//        //记录字符c 出现的频次 characterFreq[c]
//        int[] characterFreq = new int[256];
//        //two pointer (start,end)
//        int start=0,result = 0;
//        for(int end=0;end<length;end++){
//
//            //记录频次
//            characterFreq[s.charAt(end)]++;
//
//            //一旦当前字符出现重复
//            while(characterFreq[s.charAt(end)]>1){
//                characterFreq[s.charAt(start)]--;
//                //向右移动start，保证 start-end是当前最长无重复子串
//                start++;
//            }
//            //记录当前结果
//            result = Math.max(result,end-start+1);
//        }
//
//        return result;
//
//    }

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
        // 哈希集合，记录字串中每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        //two pointer (start,end) 右指针初始为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int end=-1,result = 0;
        for(int start=0;start<length;start++) {
            if (start != 0) {
                // 不是第一次枚举的话(说明左指针向右移动了)
                // 维护好occ ,清除不在窗口的字符对应记录
                occ.remove(s.charAt(start - 1));
            }
            //试探end向右移的话，滑动窗口是否还满足条件
            while (end+1 < length && !occ.contains(s.charAt(end+1))) {
                // 将这个未重复的字符记录下来
                occ.add(s.charAt(end+1));
                // end真正开始移动
                end++;
            }
            // 试探结束,判断 当前滑动窗口长度是否可以更新result
            result = Math.max(result, end - start + 1);

        }

        return result;

    }


    public static void main(String[] args) {
        Solution3 s = new Solution3();
//        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));



    }





}
//leetcode submit region end(Prohibit modification and deletion)
