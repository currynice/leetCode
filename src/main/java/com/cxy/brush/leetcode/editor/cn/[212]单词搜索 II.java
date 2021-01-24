package com.cxy.brush.leetcode.editor.cn;//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： https://www.bilibili.com/video/BV1TJ411K7hM
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 315 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /***
     * 回溯算法
     * @param board
     * @param words
     * @return
     */
//    public List<String> findWords1(char[][] board, String[] words) {
//        Set<String> result = new HashSet<>();
//        for(String word :words){
//            for(int i=0;i<board.length;i++){
//                for(int j=0;j<board[0].length;j++){
//                    if(helper(board,i,j,word,0)){
//                        result.add(word);
//                    }
//                }
//            }
//        }
//        return new ArrayList<>(result);
//    }
//
//    //board[i][j] 是否等于word.charAt(index), 且board[i][j] 的左，下，右，上 是否存在任意一个等于 word.charAt(index+1)
//    private boolean helper(char[][] board, int i, int j, String word, int index){
//        if(index==word.length()){
//            return true;//找完
//        }
//        // i,j非法,越界 或 board[i][j] 不等于word.charAt(index)
//        if(i<0||i>=board.length||j<0||j>=board[0].length||word.charAt(index)!=board[i][j]){
//            return false;
//        }
//        //暂存中心点
//        char temp = board[i][j];
//
//        //暂时将中心点置灰，因为单元格内的字母不允许被重复使用
//        board[i][j] ='*';
//
//        //开始左，下，右，上 DFS探索 word.charAt(index+1)
//        boolean founded = helper(board,i-1,j,word,index+1)||
//                helper(board,i,j+1,word,index+1)||
//                helper(board,i+1,j,word,index+1)||
//                helper(board,i,j-1,word,index+1);
//
//        //不管找没找到，还原board[i][j]，因为后续该点还会使用到
//        board[i][j] =temp;
//        return founded;
//    }

    /***
     * 字典树法(trie 需要带假删除功能，因为整个算法的时间复杂度取决于 Trie 的大小。对于 Trie 中的叶节点，一旦遍历它（即找到匹配的单词），就不需要再遍历它了。结果，我们可以把它从树上剪下来)
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        //使用候选词生成一个字典树
        TrieNode wordsTrie = new TrieNode(' ');
        for(String word:words){
            wordsTrie.insert(word);
        }

        //遍历二维网格
        boolean[][] visuted = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                helper2(board,i,j,"",wordsTrie,result,visuted);
            }
        }
        return new ArrayList<>(result);
    }

    //board[i][j] 是否是字典树trie的节点, 且board[i][j] 的左，下，右，上 是否存在任意一个是字典树trie.getSubNode(board[i][j])的节点
    private void helper2(char[][] board, int i, int j,String str, Trie trie,Set<String> result,boolean[][] visuted){
        //暂存中心点
        // i,j非法,越界 或 board[i][j]已经被访问了,结束
        if(i<0||i>=board.length||j<0||j>=board[0].length||visuted[i][j]){
            return;
        }
        str+=board[i][j];

        //str是否是trie的前缀
        if(!trie.startsWith(str)){
            //当前str不是trie的前缀，不是一个单词
            return;
        }
        //str完整的出现在trie中，意味着找到
        if(trie.search(str)){
            //board[i][j] 是字典树的叶子节点，结束
            result.add(str);
            //特殊处理:在树中删除该单词最后一个字母对应的叶子节点

        }

        //将中心点标记为已访问
        visuted[i][j] =true;

        //开始左，下，右，上 DFS探索 word.charAt(index+1)
        helper2(board,i-1,j,str,trie,result,visuted);
        helper2(board,i,j+1,str,trie,result,visuted);
        helper2(board,i+1,j,str,trie,result,visuted);
        helper2(board,i,j-1,str,trie,result,visuted);

        //不管找没找到，还原board[i][j]，因为后续该点还会使用到
        visuted[i][j] =false;
    }



   class TrieNode{
        private char val;

       private TrieNode[] children;
       //用string!=null 代替boolean表示是否是一个word，更方便
       private String word;

       public TrieNode(char x) {
           children = new TrieNode[26];
           word =null;
           val = x;
       }
   }

   private void buildTrie(){

   }




////    public static void main(String[] args) {
////        char[][] board = new char[4][4];
////        board[0] = new char[]{'o','a','a','n'};
////        board[1] = new char[]{'e','t','a','e'};
////        board[2] = new char[]{'i','h','k','r'};
////        board[3] = new char[]{'i','f','l','v'};
//        String[] words = new String[]{"lllllll","fffffff","ssss","s","rr","xxxx","ttt","eee","ppppppp","iiiiiiiii","xxxxxxxxxx","pppppp","xxxxxx","yy","jj","ccc","zzz","ffffffff","r","mmmmmmmmm","tttttttt","mm","ttttt","qqqqqqqqqq","z","aaaaaaaa","nnnnnnnnn","v","g","ddddddd","eeeeeeeee","aaaaaaa","ee","n","kkkkkkkkk","ff","qq","vvvvv","kkkk","e","nnn","ooo","kkkkk","o","ooooooo","jjj","lll","ssssssss","mmmm","qqqqq","gggggg","rrrrrrrrrr","iiii","bbbbbbbbb","aaaaaa","hhhh","qqq","zzzzzzzzz","xxxxxxxxx","ww","iiiiiii","pp","vvvvvvvvvv","eeeee","nnnnnnn","nnnnnn","nn","nnnnnnnn","wwwwwwww","vvvvvvvv","fffffffff","aaa","p","ddd","ppppppppp","fffff","aaaaaaaaa","oooooooo","jjjj","xxx","zz","hhhhh","uuuuu","f","ddddddddd","zzzzzz","cccccc","kkkkkk","bbbbbbbb","hhhhhhhhhh","uuuuuuu","cccccccccc","jjjjj","gg","ppp","ccccccccc","rrrrrr","c","cccccccc","yyyyy","uuuu","jjjjjjjj","bb","hhh","l","u","yyyyyy","vvv","mmm","ffffff","eeeeeee","qqqqqqq","zzzzzzzzzz","ggg","zzzzzzz","dddddddddd","jjjjjjj","bbbbb","ttttttt","dddddddd","wwwwwww","vvvvvv","iii","ttttttttt","ggggggg","xx","oooooo","cc","rrrr","qqqq","sssssss","oooo","lllllllll","ii","tttttttttt","uuuuuu","kkkkkkkk","wwwwwwwwww","pppppppppp","uuuuuuuu","yyyyyyy","cccc","ggggg","ddddd","llllllllll","tttt","pppppppp","rrrrrrr","nnnn","x","yyy","iiiiiiiiii","iiiiii","llll","nnnnnnnnnn","aaaaaaaaaa","eeeeeeeeee","m","uuu","rrrrrrrr","h","b","vvvvvvv","ll","vv","mmmmmmm","zzzzz","uu","ccccccc","xxxxxxx","ss","eeeeeeee","llllllll","eeee","y","ppppp","qqqqqq","mmmmmm","gggg","yyyyyyyyy","jjjjjj","rrrrr","a","bbbb","ssssss","sss","ooooo","ffffffffff","kkk","xxxxxxxx","wwwwwwwww","w","iiiiiiii","ffff","dddddd","bbbbbb","uuuuuuuuu","kkkkkkk","gggggggggg","qqqqqqqq","vvvvvvvvv","bbbbbbbbbb","nnnnn","tt","wwww","iiiii","hhhhhhh","zzzzzzzz","ssssssssss","j","fff","bbbbbbb","aaaa","mmmmmmmmmm","jjjjjjjjjj","sssss","yyyyyyyy","hh","q","rrrrrrrrr","mmmmmmmm","wwwww","www","rrr","lllll","uuuuuuuuuu","oo","jjjjjjjjj","dddd","pppp","hhhhhhhhh","kk","gggggggg","xxxxx","vvvv","d","qqqqqqqqq","dd","ggggggggg","t","yyyy","bbb","yyyyyyyyyy","tttttt","ccccc","aa","eeeeee","llllll","kkkkkkkkkk","sssssssss","i","hhhhhh","oooooooooo","wwwwww","ooooooooo","zzzz","k","hhhhhhhh","aaaaa","mmmmm"};
////        Solution212 solution212 = new Solution212();
////        solution212.findWords(board,words).forEach(System.out::println);
////    }
}
//leetcode submit region end(Prohibit modification and deletion)
