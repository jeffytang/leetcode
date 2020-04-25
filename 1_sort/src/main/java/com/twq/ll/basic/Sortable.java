package com.twq.ll.basic;

public interface Sortable<E extends Comparable<E>> {

    void sort(E[] data);

}
