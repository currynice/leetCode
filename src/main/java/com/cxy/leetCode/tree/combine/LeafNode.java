package com.cxy.leetCode.tree.combine;

import java.util.Iterator;

/**
 * Description:
 * 所以叶子节点的迭代器没有什么意义。我们可以简单地返回null，
 * 但是那样处理节点的代码就需要区分是叶子节点还是其他节点。
 * 如果我们返回的是一个NullIterator，一
 * 个看起来和其他的迭代器差不多，
 * 但是hasNext()永远返回false，而next()永远返回null的迭代器，那么叶子节点的遍历也就和其他节点没有什么两样了。
 * <br>
 * Date: 2020/5/12 9:46  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class LeafNode extends AbstractNode {
    protected LeafNode(String name) {
        super(name);
    }

    //cannot add
    public void addNode(Node node) {
       throw new UnsupportedOperationException("a leafNode does not support addNode");
    }

    public Iterator<Node> iterator() {
        return NullIterator.instance();
    }

    @Override
    public void doSomething() {
        System.out.println("i am a leafNode"+this.name);
    }
}
