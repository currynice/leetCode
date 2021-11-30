package com.cxy.brush.leetcode.editor.cn;;
//给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
//
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：3
//
//
// 示例 2：
//
//
//输入：n = 11
//输出：0
//解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 2³¹ - 1
// 第 n 位上的数字是按计数单位（digit）从前往后数的第 n 个数，参见 示例 2 。
//
// Related Topics 数学 二分查找 👍 267 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution400 {
    public int findNthDigit(int n) {
        if(n<10){
            return n;
        }

        //得到几位数
        int digit =2 ;
        int remain = n-9;

        while(remain >0){
            //当前digit位数，包含多少位
            long digitTotal = getDigitTotal(digit);

            if(remain < digitTotal){
                break;
            }

            remain -=  digitTotal;
            digit++;
        }

        //如 n =490 ,digit = 3 ,remian = 301

        // 当前所有digit位数中第 whichNum个 = 100
        int whichNum = (remain-1)/digit;

        // 返回第 whichNum个数第 whichIndex位 = 0
        int whichIndex = (remain-1)%digit;

        //三位数的start为100
        long start = getStartFromDigit(digit);

        //三位数的第100个为 200
        String res = start +whichNum +"";

        //返回200的第 0位:2
        return res.charAt(whichIndex) - '0';

    }


    // 返回 digit 位数包含的总位数
    long getDigitTotal(int digit){

        //digit=3 ,start=100
        long start = getStartFromDigit(digit);

        //digit=3 ,end=999
        long end = getEndFromDigit(digit);

        return (end -start+1)*digit;
    }

    //返回 digit 位数的开始
    long getStartFromDigit(int digit){
        //digit=3 ,start=100
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        for(int i=digit; i >1;i--){
            sb.append("0");
        }
        return  Long.parseLong(sb.toString());
    }

    //返回 digit 位数的结束
    long getEndFromDigit(int digit){
        //digit=3 ,end=999
        return getStartFromDigit(digit+1)-1;

    }




}
//leetcode submit region end(Prohibit modification and deletion)
