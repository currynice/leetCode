package com.cxy.brush.leetcode.editor.cn;
//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 251 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution844 {
    public boolean backspaceCompare(String S, String T) {
        return helper(S).equals(helper(T));
    }

    private String helper(String str){
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        Stack<String> stack = new Stack();
        for(int i = 0;i<len;i++){
            if("#".equals(String.valueOf(str.toCharArray()[i]))){
                //
                if(!stack.isEmpty()){
                    stack.pop();//删除一个
                }
            }else {
                stack.push(String.valueOf(str.toCharArray()[i]));
            }
        }
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
