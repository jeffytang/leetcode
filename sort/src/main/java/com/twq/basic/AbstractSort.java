package com.twq.basic;

public abstract class AbstractSort<E extends Comparable<E>>
        implements Sortable<E> {

    protected void swap(E[] data, int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    protected boolean less(E e1, E e2) {
        return e1.compareTo(e2) < 0;
    }

}
