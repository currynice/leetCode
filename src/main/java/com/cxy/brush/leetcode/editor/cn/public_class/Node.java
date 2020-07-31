package com.cxy.brush.leetcode.editor.cn.public_class;

import java.util.List;

/**
 * Description: N叉树  <br>
 * Date: 2020/7/31 17:39  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
