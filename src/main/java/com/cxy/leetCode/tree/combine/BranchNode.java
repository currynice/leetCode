package com.cxy.leetCode.tree.combine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * 树枝节点可以添加子节点，叶子节点或者另外一个树枝节点。
 *
 *  <br>
 * Date: 2020/5/12 10:05  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class BranchNode extends AbstractNode {

    //todo use Vector instead
    private List<Node> childs = new ArrayList<>();

    public BranchNode(String name) {
        super(name);
    }

    @Override
    public void addNode(Node node) {
        childs.add(node);
    }


    @Override
    public Iterator<Node> iterator() {
        return childs.iterator();
    }

    @Override
    public void doSomething() {
        System.out.println("i am a branchNode"+this.name);
    }
}
