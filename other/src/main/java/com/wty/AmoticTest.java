package com.wty;

import com.google.common.util.concurrent.AtomicLongMap;

public class AmoticTest {

    public static void main(String[] args) {
        AtomicLongMap<String> atomicLongMap = AtomicLongMap.create();
        System.out.println(atomicLongMap.addAndGet("key", 1L));
        System.out.println(atomicLongMap.addAndGet("key", 2L));
        System.out.println(atomicLongMap.addAndGet("key", 3L));
        System.out.println(atomicLongMap.addAndGet("key", 4L));
    }

}
