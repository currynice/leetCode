package com.cxy.taolu;

import com.cxy.brush.leetcode.editor.cn.public_class.ListNode;

import java.util.Arrays;

/**
 * Description:  双指针
 * 一.快慢指针
 * 二.左右指针
 *
 * <br>
 * Date: 2020/7/14 10:41  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class DoublePointer {

     // 一.快慢指针
     //    1.链表是否包含环的问题
     //      1.1 单链表特点：单指针的话:只知道每个节点的下一节点,next指向 null的话没有环,
     //                      但是有环的话就是死循环了
     static boolean hasCycle1(ListNode head){
        //单指针判断isCycle局限性,可能跳不出while loop
        while(head!=null){
            head = head.next;
        }
        return false;
    }
    //      2 快慢指针
    //      2.1 通过不同的步进，无环的话快的指针将先遇到null,有环的话快指针会遇到慢指针
    static boolean hasCycle2(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //出发
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
     }

    //    2.2 链表有环，返回环的起点:
    //      当快慢指针相遇时，让任一指针重新指向头节点，然后俩个以相同速度前进，再次相遇时所在的节点位置就是环开始的位置
    //
    //第一次相遇时，假设慢指针 slow 走了 k 步，那么快指针 fast 一定走了 2k 步，即比 slow 多走了 k 步（也是环的长度）。
    // 如果相遇点距环的起点的距离为 m，那么环的起点距头结点 head 的距离为 k - m，那么从 head 前进 k - m 步就能到达环起点。
    // 且从相遇点继续前进 k - m 步，也可以到达环起点。
    static ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //出发
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                break;
            }
        }
        //调整统一速度，将slow指针从头开始步进
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    //    2.3 找到链表的中点:
    //      还是快慢指针（速度2:1），当快指针到达tail，慢指针位于链表中点或中间两个节点的偏左(偶数个节点)
    //  找到中点可以 进行把链表二分进行归并排序啦
    static ListNode getCentre(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //出发
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //slow位于中点
        return slow;
    }

    // 2.4 寻找链表倒数第 k 个元素 (k小于链表长度)
    //     相当于先后指针，一个指针先出发，走k步，然后两个指针一起走，当先出发的指针到达tail, 后出发的指针就是倒数第k个node

    static ListNode getBackK(ListNode head,int k){
        ListNode fast = head;
        ListNode slow = head;
        //fast先 出发
        while(k > 0){
            fast = fast.next;
            k--;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    // 二.左右指针
    //有序数组 就可以 这么玩，两个index ，init: left = 0 ,right = length -1;

    /**
     *
     * @param nums 元素从小到大升序
     * @param target
     * @return
     */
    static int binarySearch(int[] nums,int target){
       int left = 0 ,right = nums.length -1;

       while (left<=right){
           int mid = (left + right)/2;
           if(nums[mid]==target){
               return mid;
           }else if(nums[mid]>target){
               right = mid-1;
           }else {
               left = mid+1;
           }
       }
            return -1;//not found
    }

    /**
     * 前后交换一个有序数组
     * @param nums
     * @return
     */
    static int[] swapArray(int[] nums){
        int left = 0 ,right = nums.length -1;
        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
        return nums;
    }


    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        head.next = n2;
//
//
//        ListNode n3 = new ListNode(3);
//        n2.next = n3;
//
//
//        ListNode n4 = new ListNode(4);
//        n3.next = n4;
//
//        ListNode n5 = new ListNode(5);
//        n4.next = n5;
//
//       n5.next = null;
//
//        ListNode centre =  getBackK(head,2);
//        System.out.println();

        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        int[] targetIndex = swapArray(nums);
        System.out.println(Arrays.toString(targetIndex));
    }


}
