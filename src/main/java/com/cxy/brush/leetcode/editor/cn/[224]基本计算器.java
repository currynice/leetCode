package com.cxy.brush.leetcode.editor.cn;

//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 递归 数学 字符串 
// 👍 630 👎 0



import java.util.*;
import java.util.stream.Collector;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    /**
     * 解法一: 表达式  转为 后缀表达式后求值
     * @param s  可能包含的字符：数字、'+'、'-'、'('、')'、和 ' '
     * @return
     */
    public int calculate(String s) {

        // 维护运算符的栈，只要栈顶的符号优先级 >=当前符号(乘除 > 加减 > 左括号)，就不断取出栈顶并输出
        // 遇到左括号，入栈，为了当遇到右括号时找到对应的左括号
        // 遇到右括号，不断取出栈顶并输出，直到栈顶为左括号，（ps记得将左括号出栈）
        //  其余符号正常入栈

        // 最终输出的序列就是一个等价的后缀表达式

        //处理负数，如果将'-'视为操作符，类似"-1+3"或"1+(-1+2)"类似的表达式，会有问题，因为-缺少左操作数，
        // 所以在 左括号后 ，先入一个0， 1+(0-1+2) 。
        // 操作符前先入一个0，  1+ -1 -> 1 + 0 -1 ;


        //操作符栈
        Stack<Character> ops = new Stack<>();

        //逆波兰式
        List<String> tokensList = new ArrayList<>();

        //解析遇到的数字值
        long val =0;

        //考虑到多个字符可以组成数字的情况  12  334 等
        boolean parse_num = false;

        //考虑到正负数， 在 - 前补 0
        boolean need_zero = true;

        for(char ch:s.toCharArray()){
            //如果ch是空格
            if(ch==' '){
                need_zero = false;
                continue;
            }

            if(ch>='0'&& ch<='9'){
                //遇到数字，置 parse_num 为 true
                parse_num=true;
                val = val*10+ch-'0';

                //当前字符处理完毕，判断下个字符
                continue;
            }

            if(parse_num){
                // 已经开始解析数，且当前为非数字char，说明一个数字解析完成
                // 如 12 + ，此时遇到2后面的空格，数字解析完成
                // 或者 12+ ，此时遇到2后面的+，数字也算解析完成
                //数字输出
                tokensList.add(val+"");
                val=0;
                need_zero = false;
                parse_num = false;
            }

            //左括号，入栈
            if(ch=='('){
                ops.push(ch);
                //左括号前需要补0
                need_zero = true;
                continue;
            }

            if(ch==')'){
                //弹出op直到遇到左括号
                while (!ops.isEmpty() && ops.peek()!='('){
                    tokensList.add(ops.pop()+"");
                }
                //左括号弹出
                ops.pop();
                //右括号 前不需要0
                need_zero = false;
                continue;
            }

            //当前符号是 '+' 或 '-'，加入之前，如果 need_zero ，补0
            // 0 -
            if(need_zero){
                tokensList.add("0");
            }
            //判断级别,只要栈顶的符号优先级 >=当前符号，就不断取出栈顶并输出
            while (!ops.isEmpty()&&rank(ops.peek())>=rank(ch)){
                tokensList.add(ops.pop()+"");
            }
             ops.push(ch);
             need_zero = true;
        }

        //如果最后一位是数字后面的空格, 把前面解析的数字放入表达式
        if(parse_num){
            tokensList.add(val+"");
        }
        //弹出栈内剩余元素
        while (!ops.isEmpty()){
            tokensList.add(ops.pop()+"");
        }
        String[] tokens = tokensList.toArray(new String[0]);
        return evalRPN(tokens);
    }


    private int rank(char ch){
        if(ch=='('){
            return 0;//最低
        }
        if(ch=='+' || ch=='-'){
            return 1;
        }

        if(ch=='*' || ch=='/'){
            return 2;
        }
        return 0;
    }

    /**
     * 后缀表达式（逆波兰式）求值 (+ ,-,*, / 运算)
     *
     *  1 2 + 即相当于  1 + 2
     *  4 2 / 即相当于  4 / 2
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        //维护数字栈
        // 是否数字用符号来判断，否则数字(包括负数)判断表达式太长了）
        // 遇到运算符出栈顶两个数字计算，并将结果压入栈
        List<String> operators = Arrays.asList("+","-","*","/");

        Stack<String> stack = new Stack<>();


        for(String token:tokens){
            if(!operators.contains(token)){
                //数字
                stack.push(token);
            }else {
                //遇到op， 出栈顶两个数字
                // 因为 2,1,+ 相当于 2+1，即 num2 + num1
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());


                stack.push(eval(num2,num1,token)+"");
            }
        }

        return Integer.parseInt(stack.pop());

    }


    /**
     * 四则运算
     * @param num1
     * @param num2
     * @param operators
     * @return
     */
    private int eval(int num1,int num2,String operators){
        if(operators.equals("+")){
            return num1+num2;
        }else if(operators.equals("-")){
            return num1-num2;
        }else if(operators.equals("*")){
            return num1*num2;
        }else if(operators.equals("/")){
            return num1/num2;
        }

        return 0;
    }


    public static void main(String[] args) {
        char c = '1';
        char c2 = '2';

        System.out.println(new Solution().calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
