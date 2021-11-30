package com.cxy.knowledge.list;

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

/**
 * 合并两个有序链表
 * list1:  1 3 5 7
 * list2:  1 2 4 4
 *
 * result: 1 1 2 3 4 4 5 7
 */
public class merge2List21 {

    /**
     * 递归
     * 两个链表头部较小的一个与剩下元素的 merge 结果
     * result
     * list1[0] + merge(list1[1:],list2)   list1[0] <  list2[0]
     * list2[0] + merge(list1,list2[1:])   list1[0] >= list2[0]
     *
     * 时间复杂度：O(n + m)O(n+m)。 因为每次调用递归都会去掉 l1 或者 l2 的头元素（直到至少有一个链表为空），
     * 函数 mergeTwoList 中只会遍历每个元素一次。所以，时间复杂度与合并后的链表长度为线性关系。
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode merge2List01(ListNode list2, ListNode list1) {
        //list1 空,不需要合并,返回另一个
        if(list1==null){
            return list2;
        }

        //list2 空
        else if(list2==null){
            return list1;
        }

        //  list1[0] <  list2[0]
        else if(list1.val<list2.val){
            list1.next = merge2List01(list1.next,list2);
            return list1;
        }

        else{
            list2.next = merge2List01(list1,list2.next);
            return  list2;
        }
    }

    /**
     * 设定一个哨兵节点 "prehead" ，这可以在最后让我们比较容易地返回合并后的链表。
     * 我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：如果 l1 当前位置的值小于等于 l2 ，
     * 我们就把 l1 的值接在 prev 节点的后面同时将 l1 指针往后移一个。否则，我们对 l2 做同样的操作。
     *
     * 不管我们将哪一个元素接在了后面，我们都把 prev 向后移一个元素。
     *
     * 在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，
     * 所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。
     * 这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表。
     *
     */
    public static ListNode merge2List02(ListNode list2, ListNode list1) {
        //定义哨兵节点 "prehead",使得prev合并链表的增删不用在乎边界
        ListNode prehead = new ListNode(-1);
        //prev指针
        ListNode prev = prehead;

        //迭迭代至list1,list2 有一个为空
        while(list1!=null && list2!=null){
            if(list1.val < list2.val){
                prev.next = list1;
                list1 = list1.next;
            }else{
                prev.next = list2;
                list2 = list2.next;
            }
            //无论如何,prev后移
            prev = prev.next;
        }

        prev.next = (list1==null?list2:list1);
        return prehead.next;
    }





    public static void main(String arsg[]){
        //1 2 4
        ListNode list1 = new ListNode(1);
        list1.next = (new ListNode(2));
        list1.next.next = (new ListNode(4));

        //1 3 4
        ListNode list2 = new ListNode(1);
        list2.next = (new ListNode(3));
        list2.next.next = (new ListNode(4));

        ListUtil.printList(merge2List02(list1,list2));

    }
}
