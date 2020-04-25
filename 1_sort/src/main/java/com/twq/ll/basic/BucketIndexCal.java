package com.twq.ll.basic;

public interface BucketIndexCal<E> {
    // 计算指定元素所在的 bucket 的 Index
    int cal(E e);
}
