package com.twq.ll.bs;

public class _704_BinarySearch {

    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1; // 在区间 [l...r] 中寻找 target
        while (l <= r) { // 当 l == r 的时候，区间 [l...r] 依然是有效的
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] > target)
                r = mid - 1; // target 在 [l...mid - 1] 区间中
            else
                l = mid + 1; // target 在 [mid + 1, r] 区间中
        }
        // 结束条件：l > r
        // 不需要后处理，因为所有的元素在循环中都已经处理
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length; // 在区间 [l...r) 中寻找 target
        while (l < r) { // 当 l == r 的时候，区间 [l...r) 是无效的
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] > target)
                r = mid; // target 在 [l...mid) 区间中
            else
                l = mid + 1; // target 在 [mid + 1, r) 区间中
        }
        // 结束条件：l == r
        // 需要后处理，因为在循环中，还有一个元素没有处理
        if (l != nums.length && nums[l] == target) return l;

        return -1;
    }

    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1; // 在区间 (l...r) 中寻找 target
        while (l + 1 < r) { // 只有当 l + 1 < r 的时候，区间 (l...r) 是有效的，比如 (2, 4)
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] > target)
                r = mid; // target 在 (l...mid) 区间中
            else
                l = mid; // target 在 (mid, r) 区间中
        }
        // 结束条件：l + 1 == r
        // 需要后处理，因为在循环中，还有两个个元素没有处理
        if (nums[l] == target) return l;
        if (nums[r] == target) return r;

        return -1;
    }
}
