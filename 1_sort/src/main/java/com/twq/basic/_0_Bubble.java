package com.twq.basic;

public class _0_Bubble<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        if (data == null || data.length == 0) return;

        for (int i = 0; i < data.length; i++) {
            boolean hasSwap = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (less(data[j + 1], data[j])) {
                    swap(data, j + 1, j);
                    hasSwap = true;
                }
            }

            if (!hasSwap) break;
        }
    }

}
