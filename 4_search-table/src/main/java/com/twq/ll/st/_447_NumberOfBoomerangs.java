package com.twq.ll.st;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * 输入:
 * [[0,0],[1,0],[2,0]]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 */
public class _447_NumberOfBoomerangs {
    // 注意：根据 [-10000, 10000] 这个信息，说明距离不会超过整形范围
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            // key 是 points[i] 到其他点的距离
            // value 是该距离出现的次数
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++)
                if (j != i) {
                    // 计算 points[i] 到每一个点的距离
                    Integer dis = dis(points[i], points[j]);
                    // points[i] 到每一个点相同距离的个数
                    map.put(dis, map.getOrDefault(dis, 0) + 1);
                }

            // 比如距离 points[i] 为 3 的有 3 个点 （假设这三个点是 points[a], points[b], points[c]）
            // 那么这 3 个点距离 points[i] 也是 3
            // 那么回旋镖为：
            //      i,a,b   i,a,c
            //      i,b,a   i,b,c
            //      i,c,b   i,c,a
            // 即 3 * (3 - 1) 种
            for (int cnt : map.values())
                res += cnt * (cnt - 1);

        }
        return res;
    }

    private Integer dis(int[] pointA, int[] pointB) {
        return (pointA[0] - pointB[0]) * (pointA[0] - pointB[0])
                + (pointA[1] - pointB[1]) * (pointA[1] - pointB[1]);
    }

    public static void main(String[] args) {
        _447_NumberOfBoomerangs e = new _447_NumberOfBoomerangs();
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println(e.numberOfBoomerangs(points));
    }
}
