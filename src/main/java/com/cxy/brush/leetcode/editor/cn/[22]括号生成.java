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
class Solution {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate(0,0,n,new StringBuilder());
        return result;
    }

    //result.size =2n ，leftUsed 最大为n && rightUsed 最大为n
    private void generate(int leftUsed,int rightUsed,int n,StringBuilder sb) {

        if(leftUsed ==n && rightUsed==n) {
            //result 完毕
            result.add(sb.toString());
            return;
        }
        //左括号可以随便加
        if(leftUsed < n){
            sb.append("(");
            generate(leftUsed+1,rightUsed,n, sb);
            //因为当前位置还可能放右括号
            sb.deleteCharAt(sb.length()-1);
        }

        //右括号总数要比左括号少
        if(leftUsed>rightUsed && rightUsed < n){
            sb.append(")");
            generate(leftUsed,rightUsed+1,n,sb);
            //因为当前位置还可能放左括号
            sb.deleteCharAt(sb.length()-1);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
