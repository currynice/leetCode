package com.cxy.knowledge.tree.combine;

import java.util.Iterator;
import java.util.Stack;

/**
 * Description:   <br>
 * Date: 2020/5/12 10:09  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class DepthFirstIterator implements Iterator<Node> {

    private Stack<Iterator<Node>> stack = new Stack<Iterator<Node>>();

    public DepthFirstIterator(Iterator<Node> it) {
         stack.push(it);
         }

      public boolean hasNext() {
         if (stack.isEmpty()) {
             return false;
             } else {
             Iterator<Node> it = stack.peek();
             if (it.hasNext()) {
                 return true;
                 } else {
                 stack.pop();
                 return hasNext();
                 }
             }
         }


       public Node next() {
         if (hasNext()) {
           Iterator<Node> it = stack.peek();
            Node next = it.next();
            if (next instanceof BranchNode) {
                stack.push(next.iterator());
                }
             return next;
             } else {
             return null;
             }
        }

 }

