package com.cxy.leetCode.tree.combine;

import java.util.Iterator;

/**
 * Description:   可以是叶子节点(无子节点)，也可以是中间节点（有子节点的）<br>
 * Date: 2020/5/12 9:42  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public interface Node {
    void addNode(Node node);

    Iterator<Node> iterator();

    void doSomething();
}
