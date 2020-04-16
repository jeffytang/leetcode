package com.twq.basic;

//TODO
public class _6_Heap<E extends Comparable<E>> extends AbstractSort<E> {

    private E[] array;
    private int size = 0;

    public _6_Heap(int maxSize) {
        array = (E[])new Object[maxSize];
    }

    private void swim(int k) {

    }

    public void insert(E e) {
        array[++size] = e;

    }

    @Override
    public void sort(E[] data) {

    }


}
