package com.cxy.leetCode.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树prefix tree ,字典树trie 节点
 */
public class TrieNode {

    /**
     * true 节点为关键词的终结 ；false 不是
     */
    private boolean end = false;

    /**
     * 该节点的所有子节点
     * key 下一个字符
     * value 对应节点
     */
    private Map<Character, TrieNode> subNodes = new HashMap<>();


    /**
     * 添加节点树
     *
     * @param key 词
     */
    public void addSubNodes(Character key, TrieNode node) {
        subNodes.put(key, node);
    }

    /**
     * 根据字符,获取下个节点
     *
     * @return TrieNode
     */
    TrieNode getSubNode(Character key) {
        return subNodes.get(key);
    }

    /**
     * 是否是一个单词的最后一个字符(即叶子结点)
     *
     * @return
     */
    boolean isKeywordEnd() {
        return end;
    }

    /**
     * 设置为叶子结点
     * @param end
     */
    public void setKeywordEnd(boolean end) {
        this.end = end;
    }

    /**
     * 当前结点作为先辈结点，获得自己后辈结点的长度
     * @return
     */
    public int getSubNodeCount() {
        return subNodes.size();
    }
}


