package com.cxy.brush.leetcode.editor.cn;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 哈希表 字符串 回溯
// 👍 1404 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution17 {



    //九宫格数字键盘
    private  Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};



    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();


        if(digits.equals("") || digits.length()==0){
            return res;
        }

        back(digits,new StringBuilder(),0,res);
        return res;
    }

    /**
     * 2345：
     * 第一次递归函数 处理完第一个字符2之后，将输入的字符改变成"345"并第二次调用递归函数
     * 第二次递归处理3，将字符串改变成"45"后再次递归
     * 第三次递归处理4，将字符串改变成"5"后继续递归
     * 第四次递归处理5，将字符串改变成""后继续递归
     * 最后发现字符串为空了，将结果放到变量中并返回
     */
//    public void back0(String str, StringBuilder letter,int index) {
//        //递归终止条件，如果每次sub截掉字符串的一部分，"2345"变成"345"，再变成"45"，最后变成"5"，性能不佳
//        //改成用index记录每次开始处理的字符串位置，这样性能更好
//        if (index == str.length()) {
//            res.add(letter.toString());
//            return;
//        }
//        //获取index 处的字符，假设输入的字符是"2345"
//        //第一次index=0递归时c=2，第二次index=1 c=3，第三次index=2,c=4，第四次index=3, c=5
//        char c = str.charAt(index);
//        //c=2，"abc"
//        String map_string = phoneMap.get(c);
//        //遍历字符串，比如第一次得到的是2，页就是遍历"abc"
//        for (int i = 0; i < map_string.length(); i++) {
//            letter.append(map_string.charAt(i));
//            back0(str, letter, index + 1);
//            //回溯
//            letter.deleteCharAt(letter.length() - 1);
//        }
//    }

    public void back(String str, StringBuilder letter,int index,List<String> res) {

        // str.subString(index) isEmpty
        if(index==str.length()){
            res.add(letter.toString());
            return;
        }

        char c = str.charAt(index);

        String letters_map = phoneMap.get(c);

        for(int i=0;i<letters_map.length();i++){
            letter.append(letters_map.charAt(i));
            back(str,letter,index+1,res);
            //删除最后一个字符，回溯
            letter.deleteCharAt(letter.length()-1);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations(""));
    }


    }
//leetcode submit region end(Prohibit modification and deletion)
