package com.twq.basic;

import com.google.common.base.Stopwatch;

import java.util.Random;

public class SortCompare {

    private static Random random = new Random();

    public static long time(String sort, Integer[] data) {
        Stopwatch timer = Stopwatch.createStarted();
        if (sort.equals("selection")) new _1_Selection<Integer>().sort(data);
        if (sort.equals("insertion")) new _2_Insertion<Integer>().sort(data);
        if (sort.equals("shell")) new _3_Shell<Integer>().sort(data);
        return timer.elapsed().toMillis();
    }

    public static long timeRandomInput(String sort, int n, int t) {
        long total = 0L;
        Integer[] a = new Integer[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++)
                a[j] = random.nextInt();
            total += time(sort, a);
        }
        return total;
    }

    public static void main(String[] args) {
        double t1 = timeRandomInput("selection", 1000, 100);
        double t2 = timeRandomInput("insertion", 1000, 100);
        double t3 = timeRandomInput("shell", 1000, 100);

        System.out.println(t1 / t2);
    }
}
