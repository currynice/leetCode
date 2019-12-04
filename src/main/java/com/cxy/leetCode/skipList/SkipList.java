package com.cxy.leetCode.skipList;

/**
 * 跳表，存储有序不重复的数据集和，基于链表+索引层 进行改造
 */
public class SkipList<E extends Comparable<? super E>> {

    //跳表更新索引数量概率
    private static final float SKIPLIST_P = 0.5f;

    //最高层数
    private static final int MAX_LEVEL = 16;

    //当前层数,即有效层数
    private int levelCount = 1;

    private final  Node<E> head = new Node<E>(MAX_LEVEL,null);  // 带头链表

    /**
     * 跳表中是否包含
     * @param val
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Node find(int val) {
        Node p = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data.compareTo(val)<0) {
                // 找到前一节点
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data.compareTo(val)==0) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void insert(E val) {
        int level = head.forwards[0]==null?1:randomLevel();
        //levelcount加到至少level
        if(level>levelCount){
            level = ++levelCount;
        }

        Node newNode = new Node(level,val);
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        Node p = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data.compareTo(val)<0) {
                // 找到前一节点
                p = p.forwards[i];
            }
            // levelCount 会 > level，所以加上判断
            if (level > i) {
                update[i] = p;
            }

        }
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }



    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data.compareTo(value)<0) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data.compareTo(value)==0) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data.compareTo(value)==0) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }





    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }





    public class Node<E extends Comparable<? super E>> {

        /**
         * 结点存的值
         */
        private E data;

        /**
         * 结点指向第i级的结点，forwards[0]就是原始结点 ,forwards[3]就是第三层的所有结点
         */
        private Node<E>[] forwards;


        public Node(int MAX_LEVEL, E data) {
            this.data = data;
            this.forwards = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(" }");

            return builder.toString();
        }
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }


    public static void main(String args[]){
        SkipList<Integer> list = new SkipList<>();
        list.insert(1);
        list.insert(2);
        list.printAll_beautiful();
        list.insert(4);
//        list.insert(5);
//        list.insert(6);
//        list.insert(8);
//        list.insert(7);


    }
}









