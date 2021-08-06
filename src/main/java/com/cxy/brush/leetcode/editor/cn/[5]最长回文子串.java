package com.cxy.brush.leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3511 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {


    public String longestPalindrome(String s) {

        int length = s.length();

        //单字符字符串是回文
        if(1==length){
            return s;
        }

        // dp[i][j] 代表 子串[i,j] 是否是回文
        int[][] dp = new int[length][length];

        //init
        for(int a=0;a<length;a++){
            dp[a][a] = 1;
        }

        //遍历过程中 回文子串 的最大长度
        int maxLen = 1;

        //结果回文子串的左右 index ，当maxLen更新时 更新
        int resultLeft=0,resultRight=0;


        //假设length=5,这样遍历以确保每次遍历时所需的情况已经计算过
        //j=1; i=0
        //j=2; i=0,i=1
        //j=3; i=0,i=1,i=2
        //j=4; i=0,i=1,i=2,i=3
        for(int j = 1;j<length;j++){
            for(int i = 0;i<j;i++){
//                System.out.println("开始算dp["+i+"]["+j+"]");

                boolean baseCase = (s.charAt(i) == s.charAt(j));

                if(baseCase){
                    if(j-i+1<3){
                        dp[i][j] =1;
                    }else {
                        // 如 dp[1,4] 需要 dp[2,3]的值，根据我们的遍历顺序，肯定之前已经计算了
                        dp[i][j] = dp[i+1][j-1];
//                        System.out.println("---------需要 dp ["+(i+1)+"]["+(j-1)+"]------------");

                    }
                }

                if(dp[i][j]==1 && j-i+1 >maxLen){
                    maxLen = j-i+1;
                    resultLeft = i;
                    resultRight = j;
                }
//                System.out.println("---------计算dp["+i+"]["+j+"]结束------------");

            }
        }
        //subString api 左闭 右开
        return s.substring(resultLeft,resultRight+1);
    }



//    public static void main(String[] args) {
//        Solution5 s= new Solution5();
////        System.out.println(s.longestPalindrome("a"));
////        System.out.println(s.longestPalindrome("ac"));
//        System.out.println(s.longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
