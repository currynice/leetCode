package com.cxy.brush.leetcode.editor.cn;//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计 
// 👍 745 👎 0


//class LRUCache extends LinkedHashMap<Integer, Integer> {
//    private int capacity;
//
//    public LRUCache(int capacity) {
//        super(capacity, 0.75F, true);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        return super.getOrDefault(key, -1);
//    }
//
//    public void put(int key, int value) {
//        super.put(key, value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//        return size() > capacity;
//    }
//}

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
//双链表节点 + 哈希表

//靠头部的数据是最近使用的，靠尾部的数据是最久未使用的。
class LRUCache {
    //双链表节点
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer,DLinkedNode> cache = new HashMap<>();

    //size of 双链表
    private int size;

    // 容量
    private int capacity;

    // head tail 虚拟节点
    private DLinkedNode head,tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //伪头部和伪尾点
        head = new DLinkedNode(0,0);
        tail = new DLinkedNode(0,0);
        head.next = tail;
        tail.prev  = head;
    }


    public int get(int key) {
        DLinkedNode target = cache.get(key);
        if(null == target)
            return -1;
        //将查询元素提升为最近使用
        makeRecently(key);
        return target.value;
    }


    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (null == node) {
            if(size == capacity){
                removeLeastRecently();
            }
            // 创建一个新的节点,加到head
            addRecently(key,value);
        } else {
            // 如果 key 存在，先删除，再更新 value，并移到头部
            deleteKey(key);
            addRecently(key,value);
        }

    }




    //function  to operate linkedList

    // 在头部添加元素O(1) : 需要维护好三个相关节点的prev,next指针
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        ++size;
    }


    //删除某个节点 O(1), 假设 此节点一定存在
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }


    //弹出(删除)队尾队尾元素
    private DLinkedNode removeTail() {
        if(tail.prev ==null){
            return null;
        }
        DLinkedNode last = tail.prev;
        removeNode(last);
        return last;// only value exist
    }

    //operate  LurCache api 统一封装操作 LurCache 的api ，操作双链表以及哈希表

    // 将某个 key(node) 提升为最近使用的 将该key对应的元素移动到队头 moveToHead
    private void makeRecently(int  key) {
        DLinkedNode node = cache.get(key);
        //删除
        removeNode(node);
        //放入对头
        addToHead(node);
        //维护map
        cache.put(key,node);
    }


    // 将某个 key(node) 设置为最近使用的 将该key对应的元素移动到队头 moveToHead
    private void addRecently(int  key,int value) {
        DLinkedNode node =  new  DLinkedNode (key,value);
        //放入对头
        addToHead(node);
        //维护map
        cache.put(key,node);
    }

    //删除LruCache中的某个key
    private void deleteKey(int  key) {
        DLinkedNode node = cache.get(key);
        //删除
        removeNode(node);
        //map中也删除
        cache.remove(key);
    }

    /* 队满了，删除最久未使用的元素 */
    private void removeLeastRecently() {
        // 链表尾部元素是最久未使用的
        DLinkedNode deletedNode = removeTail();
        // 从 map 中删除它的 key
        cache.remove( deletedNode.key);
    }


    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1,1);
        lru.put(2,2);
        System.out.println(lru.get(1));
        lru.put(3,3);
        System.out.println(lru.get(2));
        lru.put(4,4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
