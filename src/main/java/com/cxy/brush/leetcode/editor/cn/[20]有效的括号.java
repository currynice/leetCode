package com.cxy.brush.leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1695 👎 0


import java.util.HashMap;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution20 {
    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    public Solution20() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {
        if(s.length() %2 ==1){
            //奇数个字符肯定不合法
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            //左括号入栈
            if(mappings.get(c)==null){
                stack.push(c);
            }else {
                //右括号 需要 和弹出栈顶元素进行匹配
                Character top = stack.isEmpty()?'#':stack.pop();
                if(top!=mappings.get(c)){
                    //不匹配的话 判定valid
                    return false;
                }
            }

        }

        return stack.isEmpty();

    }


}
//leetcode submit region end(Prohibit modification and deletion)
