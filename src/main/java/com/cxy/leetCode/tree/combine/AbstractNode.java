package com.cxy.leetCode.tree.combine;

/**
 * Description:   <br>
 * Date: 2020/5/12 9:44  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public abstract class AbstractNode implements Node {
    protected String name;

    protected AbstractNode(String name) {
        this.name = name;
    }


    public String toString() {
        return name;
    }

}
