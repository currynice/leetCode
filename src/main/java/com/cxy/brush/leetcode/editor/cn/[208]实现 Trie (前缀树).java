package com.cxy.brush.leetcode.editor.cn;

//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 504 👎 0


import org.apache.commons.lang3.CharUtils;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {


    /**
     * true :节点为关键词的终结 ；false 不是
     */
    private boolean end = false;

    /**
     * 该节点的所有子节点(256个，英文+字符)
     * 此题：26个小写英文
     * key 下一个字符
     * value 对应节点
     */
    private Map<Character, Trie> subNodes = new HashMap<>(26);


    /**
     * 添加节点
     *
     * @param key 词
     * @param node key对应的node
     */
    private void addSubNodes(Character key, Trie node) {
        subNodes.put(key, node);
    }

    /**
     * 获取字符对应的节点
     *
     * @return TrieNode
     */
    private Trie getSubNode(Character key) {
        return subNodes.get(key);
    }

    /**
     * 是否是一个单词的最后一个字符(即叶子结点)
     *
     * @return
     */
    private boolean isKeywordEnd() {
        return end;
    }

    /**
     * 设置为叶子结点
     * @param end
     */
    private void setKeywordEnd(boolean end) {
        this.end = end;
    }

    /**
     * 获得当前结点作获得的后辈结点数量
     * @return
     */
    private int getSubNodeCount() {
        return subNodes.size();
    }



    /**
     * 本题题意: a-z
     * 东亚文字范围： 0x2E80(11904)-0x9FFF(40959) (东亚文字范围)）
     *
     *  (不是字母数字字符)    或  非东亚文字  true直接跳过
     */
    private boolean isSymbol(char c) {
        int ic = (int) c;
//        return (!CharUtils.isAsciiAlphaLower(c)) && (ic < 0x2E80 || ic > 0x9FFF);
          return !(c >= 'a' && c <= 'z');
    }


    //public API start
    /** Initialize your data structure here. */
    public Trie() {

    }
    
    /** Inserts a word into the trie. 根据一个单词，补充相关结点*/
    public void insert(String word) {
        Trie tempNode = this;//把当前节点作为起点
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            //不处理题意以外的字符
            if (isSymbol(c)) {
                continue;
            }
            //找到当前字符对应的结点
            Trie node = tempNode.getSubNode(c);
            if (node == null) {
                node = new Trie();
                tempNode.addSubNodes(c, node);
            }
            tempNode = node;//进入下个节点
            if (i == word.length() - 1) {
                // 关键词结束， 设置结束标志
                tempNode.setKeywordEnd(true);
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word==null||word.equals("")){
            return false;
        }
        Trie tempNode = this;//根结点开始出发
        //试探word.length次
        for(int i=0;i<word.length();++i){
            if(tempNode.getSubNode(word.charAt(i))==null){
                return false;
            }
            tempNode=tempNode.getSubNode(word.charAt(i));
        }
        //如果结束，代表该word存在
        return tempNode.isKeywordEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie tempNode = this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<prefix.length();++i){
            if(tempNode.getSubNode(prefix.charAt(i))==null){
                return false;
            }
            tempNode=tempNode.getSubNode(prefix.charAt(i));
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
