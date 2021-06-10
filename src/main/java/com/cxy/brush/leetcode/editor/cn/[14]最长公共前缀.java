package com.cxy.brush.leetcode.editor.cn;
//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1639 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution14 {

    public String longestCommonPrefix0(String[] strs) {
      if(strs==null || strs.length ==0 ){
          return "";
      }

      // 以第一个字符串为基础进行比较
      String result = strs[0];
      for(int i=1; i<strs.length;i++){
          //待比较的字符串
          String curr = strs[i];
          result = longestCommonPrefix(result,curr);
          if(result.length()==0){
            break;
          }
      }
        return result;

    }

    /**
     * 方法一：返回两个字符串的公共前缀[容易超时]
     * @param str1
     * @param str2
     * @return
     */
    private  String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 二分法：最长公共前缀的长度不会超过字符串数组中的最短字符串的长度。
     * 用 minLength 表示字符串数组中的最短字符串的长度，在 [0,minLength] 的范围内通过二分查找得到最长公共前缀的长度。
     * 每次取查找范围的中间值 mid，依次判断每个字符串的长度为 mid 的前缀是否相同，
     *    1.如果相同，最长公共前缀的长度一定大于或等于mid，
     *    2.如果不相同，最长公共前缀的长度一定小于 mid
     *
     */
    public  String longestCommonPrefix(String[] strs) {
        if (strs==null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        int low = 0,high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;

            if (prefixesAllEquals(mid,strs)) {
                //如果每个字符串的长度为 mid 的前缀都相同，最长公共前缀的长度一定大于或等于mid
                low = mid;
            } else {
                //否则最长公共前缀的长度一定小于 mid
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);

    }


    //依次判断每个字符串的长度为 length 的前缀是否相同，
    public  boolean prefixesAllEquals(Integer prefixLength,String[] strs){
        if(strs[0].length()<prefixLength){
            return false;
        }
        String publicStr  = strs[0].substring(0,prefixLength);
        for(int i=1;i<strs.length;i++){
            if(strs[i].length()<prefixLength || !publicStr.equals(strs[i].substring(0,prefixLength))){
                return false;
            }
        }
        return true;

    }




}
//leetcode submit region end(Prohibit modification and deletion)
