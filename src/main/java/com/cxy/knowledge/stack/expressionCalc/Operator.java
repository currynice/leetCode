package com.cxy.knowledge.stack.expressionCalc;


import java.util.Arrays;
import java.util.List;

/**
 * 运算符号 todo 将运算符号按优先级进行排序，学了排序再写
 */
public class Operator {

    public List<String> allowedOperators= Arrays.asList("+","-","*","/");

    private String symbol;

    //运算符级别(按照标准 classpath:file/Java运算符.png)
    // 1 级最高，14 级最低。优先级高的先执行
    private int leval;

    public Operator(String symbol, int leval) {
        this.symbol = symbol;
        this.leval = leval;
    }

    /**
     * 判断操作符是否是允许的
     * @param symbol0
     * @return
     */
    public boolean isIllegalOperator(String symbol0){

        return allowedOperators.contains(symbol0);
    }





    /**
     * 加法
     */
    public static final Operator addition = new Operator("+",4);


    /**
     * 减法
     */
    public static final Operator sub = new Operator("-",4);

    /**
     * 乘法
     */
    public static final Operator mult = new Operator("*",3);


    /**
     * 除法
     */
    public static final Operator div = new Operator("/",3);


}
