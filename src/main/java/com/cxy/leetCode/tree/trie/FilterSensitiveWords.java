package com.cxy.leetCode.tree.trie;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FilterSensitiveWords {

    private static final String REPLACEMENT = "***"; //敏感词的替换文本

    //根
    private  TrieNode rootNode = new TrieNode();

    //读取文件中的敏感词,构造单词前缀树
    public void sensitiveWordsTreeBuilder ()throws Exception {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
             InputStreamReader reader = new InputStreamReader(is, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(reader)) {//关闭流

            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                addWord(lineTxt.trim());
            }
        } catch (Exception e) {
            System.err.println("读取敏感词出错");
            e.printStackTrace();
        }
    }

    /**
     * 根据一个单词，添加相关结点
     * @param lineTxt
     */
    private void addWord(String lineTxt) {
        TrieNode tempNode = rootNode;//当前节点(根节点)
        for (int i = 0; i < lineTxt.length(); i++) {
            Character c = lineTxt.charAt(i);
            if (isSymbol(c)) {
                continue;
            }
            //获得这个字符对应的结点
            TrieNode node = tempNode.getSubNode(c);
            if (node == null) {
                node = new TrieNode();
                tempNode.addSubNodes(c, node);
            }
            tempNode = node;//进入下个节点
            if (i == lineTxt.length() - 1) {
                // 关键词结束， 设置结束标志
                tempNode.setKeywordEnd(true);
            }
        }
    }


    /**
     * （a-zA-Z0-9  且  0x2E80(11904)-0x9FFF(40959) (东亚文字范围)）
     *
     *  (不是字母数字字符)    或  非东亚文字  true直接跳过
     */
    private boolean isSymbol(char c) {
        int ic = (int) c;
        return (!CharUtils.isAsciiAlphanumeric(c)) && (ic < 0x2E80 || ic > 0x9FFF);
    }

    /**
     * 过滤，返回过滤后的结果，基于树 的深度优先搜索
     * @param text
     * @return
     */
    public String fliter(String text){
        StringBuilder result = new StringBuilder();
        if(StringUtils.isBlank(text)){
            return text;
        }
        TrieNode tempNode = rootNode;//根结点开始出发
        int begin = 0;//记录回滚点
        int position = 0;//移动点
        //移动最多text.length次
        while(position<text.length()){
            char c = text.charAt(position);
            if(isSymbol(c)){//如果肯定不在树里
                if (tempNode == rootNode) {//刚开始判断,放到最后，不影响
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }
            tempNode =tempNode.getSubNode(c);
            //匹配结束
            if(tempNode==null){
                //以begin开始的String没有敏感词
                result.append(text.charAt(begin));
                // 跳到下一个字符开始测试
                position = begin+1;
                begin = position;
                tempNode = rootNode;
            }else if(tempNode.isKeywordEnd()){//发现敏感词，begin->position 替换
                result.append(REPLACEMENT);
                position +=1;
                begin = position;
                tempNode = rootNode;
            }else{
                ++position;
            }
        }
        result.append(text.substring(begin));
        return result.toString();

    }

    public static void main(String []args){
        FilterSensitiveWords sensitiveWords = new FilterSensitiveWords();
        sensitiveWords.addWord("色情");
        sensitiveWords.addWord("赌球");
        System.out.println(sensitiveWords.fliter("一个色情网站"));


    }


}
