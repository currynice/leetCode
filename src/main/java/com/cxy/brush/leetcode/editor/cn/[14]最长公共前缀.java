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




    /**
     * 方法一：遍历 返回第一个字符串和其余字符串的公共前缀[容易超时]
     * @return
     */
    public String longestCommonPrefix0(String[] strs) {
        if (strs==null || strs.length == 0) {
            return "";
        }

        String first = strs[0];
        String result = first;

        for(int i=1;i<strs.length;i++){
            //两两返回共同前缀
            Integer commonLength_this_time = maxCommonLength(result,strs[i]);
            result = result.substring(0,commonLength_this_time);
            if(commonLength_this_time==0){
                break;
            }
        }

        return result;

    }

    /**
     * 返回两个字符串的公共前缀
     * @param str1
     * @param str2
     * @return
     */
    private Integer maxCommonLength(String str1, String str2) {

        Integer index = 0;

        Integer num = Math.min(str1.length(),str2.length());


        while (num>index && str1.charAt(index) == str2.charAt(index)){
            index++;
        }
        return index;
    }


    /**
     * 二分法：
     * 因最长公共前缀的长度不会超过字符串数组中的最短字符串的长度minLength。
     *   在 [0,minLength] 的闭区间范围内通过二分查找得到最长公共前缀的长度。
     *
     * 每次取查找范围的中间值 mid，依次判断每个字符串的长度为 mid 的前缀是否相同，
     *    1.如果相同，最长公共前缀的长度一定大于或等于mid，
     *    2.如果不相同，最长公共前缀的长度一定小于 mid
     */
    public  String longestCommonPrefix(String[] strs) {
        if (strs==null || strs.length == 0) {
            return "";
        }
        int minLength  = Integer.MAX_VALUE;
        for(int i=0;i<strs.length;i++){
            minLength = Math.min(minLength,strs[i].length());
        }

        //在 [0,minLength] 之间二分
        int left = 0,right = minLength;
        while(left < right){

            int mid = (right - left + 1) / 2 + left;

            if(!prefixAllEquals(mid,strs)){
                right = mid-1;
            }else{
                //如果每个字符串的长度为 mid 的前缀都相同，最长公共前缀的长度一定大于等于mid
                left = mid;
            }
        }
        return strs[0].substring(0, left);

    }

    /**
     * 判断 strs 数组所有元素的 长度为n 的前缀是否相等
     * @param n
     * @param strs
     * @return
     */
    private boolean prefixAllEquals(Integer n ,String[] strs){
        if(strs[0].length() < n){
            return false;
        }
        //假设的指定公共前缀
        String publicStr  = strs[0].substring(0,n);
        for(int i=1;i<strs.length;i++){
            if(strs[i].length() < n || !publicStr.equals(strs[i].substring(0,n))){
                return false;
            }
        }
        return true;
    }




    }
//leetcode submit region end(Prohibit modification and deletion)
