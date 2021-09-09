package com.cxy.brush.leetcode.editor.cn;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1505 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22{

    List<String> result = new ArrayList<>();


    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return   result;
        }
//        generate(n,n,"");
//        generate2(0,0,n,"");
        generate3(0,0,n,new StringBuilder());
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new Solution22().generateParenthesis(2));

    }




    /**
     * dfs 遍历
     * @param leftNotUsed  左括号未使用数
     * @param rightNotUsed 右括号未使用数
     * @param str
     */
    private void generate(int leftNotUsed,int rightNotUsed,String str) {


        if(leftNotUsed ==0 && rightNotUsed==0) {
            result.add(str);
            return;
        }

        //剩余左右括号数相等,只能添加左括号
        if(leftNotUsed == rightNotUsed){
            generate(leftNotUsed-1,rightNotUsed,str+"(");
        }else if(leftNotUsed < rightNotUsed){
            //剩余左括号小于剩余右括号，下一个可以用左括号也可以用右括号
            if(leftNotUsed > 0){
                generate(leftNotUsed-1,rightNotUsed,str+"(");
            }
            generate(leftNotUsed,rightNotUsed-1,str+")");
        }
    }


    /**
     *
     * @param leftUsed  左括号使用数
     * @param rightUsed 右括号使用数
     * @param n
     * @param currStr
     */
    private void generate2(int leftUsed,int rightUsed,int n,String currStr) {

        if(leftUsed ==n && rightUsed==n) {
            result.add(currStr);
            return;
        }


        // 剪枝
        if (leftUsed < rightUsed) {
            return;
        }

        if (leftUsed < n) {
            generate2(leftUsed + 1, rightUsed, n, currStr+"(");
        }
        if (rightUsed < n) {
            generate2(leftUsed, rightUsed + 1, n, currStr+")");
        }
    }


    private void generate3(int leftUsed,int rightUsed,int n,StringBuilder sb) {

        if(leftUsed ==n && rightUsed==n) {
            result.add(sb.toString());
            return;
        }


        // 剪枝
        if (leftUsed < rightUsed) {
            return;
        }

        if (leftUsed < n) {
            generate3(leftUsed + 1, rightUsed, n, sb.append("("));
//            因为append操作改变了传入generate3d的sb的状态
            sb.deleteCharAt(sb.length()-1);
        }
        if (rightUsed < n) {
            generate3(leftUsed, rightUsed + 1, n, sb.append(")"));
//            因为append操作改变了传入generate3d的sb的状态
            sb.deleteCharAt(sb.length()-1);

        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
