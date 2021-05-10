package com.base.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wty
 * @date 2021/4/29 14:19
 */
public class StreamTest {
    public static void main(String[] args) {
        int length = 100000;
        List<Integer> arr = new ArrayList<>(length);
        List<Integer> arr2 = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            arr.add(i);
            arr2.add(i);
        }

        long start = System.currentTimeMillis();
        arr.parallelStream().filter(x -> x % 10 == 0).collect(Collectors.toList());
        long endP = System.currentTimeMillis();
        arr2.stream().filter(x -> x % 10 == 0).collect(Collectors.toList());
        long endS = System.currentTimeMillis();
        System.out.println("并发流使用：" + (endP - start));
        System.out.println("顺序流使用：" + (endS - endP));
    }
}
