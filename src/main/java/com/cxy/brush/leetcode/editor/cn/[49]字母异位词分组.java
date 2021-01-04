package com.cxy.brush.leetcode.editor.cn;//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 400 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        //
        Map<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){

            String key = hashFunction(strs[i]);
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else{
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(key,temp);
            }
        }
        return new ArrayList<>(map.values());
    }


    private String hashFunction(String str){
        int[] num = new int[26];
        for(int j=0;j<str.length();j++){
            num[str.charAt(j)-'a'] ++;
        }
        //abc -> [0,1,2,0,0,0...]   cba-> [0,1,2,0,0,0...]
        String key = "";
        for(int j=0;j<num.length;j++){
            key = key+ num[j]+"#";// 0#1#2#0#0#0#0#0##0#0#0#0#0#...
        }
        return key;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
