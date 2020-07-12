package com.cxy.leetCode.tree.combine;

import java.util.Iterator;

/**
 * Description:   <br>
 * Date: 2020/5/12 10:20  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class TestCombine {

    static Node createTree() {
         Node root = new BranchNode("Root");
         Node a = new BranchNode("A");
         Node b = new LeafNode("B");
         Node c = new BranchNode("C");
         Node d = new BranchNode("D");
         Node e = new LeafNode("E");
         Node f = new LeafNode("F");

         a.addNode(d);
         d.addNode(e);

         c.addNode(f);

         root.addNode(a);
         root.addNode(b); root.addNode(c);
        return root;
    }



//Root
//A
//D
//E
//B
//C
//F

    static void depthFirstIterate(Node tree) {
        tree.doSomething();
        for (Iterator<Node> it = new DepthFirstIterator(tree.iterator()); it.hasNext();) {
            it.next().doSomething();
        }
    }
    


        public static void main(String args[]){
            depthFirstIterate(createTree());

        }
}
