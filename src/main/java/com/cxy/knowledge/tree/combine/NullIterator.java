package com.cxy.knowledge.tree.combine;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:   空的迭代器, hasNext() 总是返回false<br>
 * Date: 2020/5/12 9:54  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class NullIterator<T> implements Iterator<T> {
    private static final NullIterator<Object> instance = new NullIterator<>();

    /**
     * Returns a global instance of the NullIterator.
     */
    @SuppressWarnings("unchecked")
    public static <T>NullIterator<T> instance() {
        return   (NullIterator<T>)instance;
    }


    private NullIterator() {
        //NON
    }

    public boolean hasNext() {
        return false;
    }

    public T next() {
        throw new NoSuchElementException("null iterator");
    }


}
