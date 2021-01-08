package com.cxy.taolu;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Description: 贪心算法   <br>
 * Date: 2021/1/8 10:53  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class Greedy  {

    // Part 1 调度区间 Interval Scheduling

    /**
     * 问题描述:
     *     给你很多形如[start,end]的闭区间，设计算法，算出这些区间中最多有几个互不相交的区间(边界相同并不算相交)。
     *
     *  int intervalScheduling(int[][] intvs) {}
     *   ints=[[1,3],[2,4],[3,6]]，最多有两个区间互不相交，即[[1,3],[3,6]]，返回 2
     *
     * 类似 今天可以参加好几个活动，每个活动用一个区间[start,end]表示开始和结束的时间，求今天能参加几个活动呢
     */
    public int intervalSchedule(int[][] intvs){
        if(intvs.length ==0){
            return 0;
        }
        //按 end 排序所有区间
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] -o2[1];
            }
        });
        //result至少为1
        int result = 1;
        //排序后第一个区间的end
        int _end = intvs[0][1];
        for(int[] interval : intvs){
            int this_start = interval[0];
            if(this_start >= _end){
                //该区间和上一个选定区间不冲突，更新
                result++;
                _end = interval[1];
            }
        }
        return result;
    }




}
