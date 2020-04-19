package com.twq.basic;

public interface Sortable<E extends Comparable<E>> {

    void sort(E[] data);

}
