package com.twq.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 */
public class _406_QueueReconstructionByHeight {
    /*
        排序：
            1. 先按身高降序排列
            2. 在身高相等的情况下，按照所在的位置 k 进行升序排列
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> res = new LinkedList<>();
        // 逐个地把它们放在输出队列中，索引等于它们的 k 值。
        for (int[] p : people) {
            res.add(p[1], p);
        }

        return res.toArray(new int[people.length][2]);
    }
}
