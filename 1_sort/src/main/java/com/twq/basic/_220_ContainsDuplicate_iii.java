package com.twq.basic;


import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 *
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 *
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 */
public class _220_ContainsDuplicate_iii {
    // 桶排序
    // 我们设计一些桶，让他们分别包含区间 ..., [0,t], [t+1, 2t+1], .....。我们把桶来当做窗口
    // 于是每次我们只需要检查 xx 所属的那个桶和相邻桶中的元素就可以了
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;

        // key 表示元素所属的 bucket
        // value 表示元素
        Map<Integer, Integer> map = new HashMap<>();
        int buckets = t + 1;
        for (int i = 0; i < nums.length; i++) {
            int bucket = getBucketId(nums[i], buckets);

            // 如果当前元素对应的桶中已经包含元素，那么直接返回 true
            // 因为相同的元素的差等于 0
            if (map.containsKey(bucket))
                return true;

            // 查看当前 bucket 的左右邻居，看看是否符合条件
            if (map.containsKey(bucket - 1)
                    && Math.abs(nums[i] - map.get(bucket - 1)) < buckets)
                return true;

            if (map.containsKey(bucket + 1)
                    && Math.abs(nums[i] - map.get(bucket + 1)) < buckets)
                return true;

            // 将当前元素放到对应的 bucket 中
            map.put(bucket, nums[i]);
            // 维护窗口
            if (i >= k) map.remove(getBucketId(nums[i - k], buckets));
        }
        return false;
    }

    private int getBucketId(int element, int buckets) {
        return element < 0 ? (element + 1) / buckets - 1 : element / buckets;
    }

    public static void main(String[] args) {
        _220_ContainsDuplicate_iii e = new _220_ContainsDuplicate_iii();
        e.containsNearbyAlmostDuplicate(new int[]{2, 1}, 1, 1);
    }
}
