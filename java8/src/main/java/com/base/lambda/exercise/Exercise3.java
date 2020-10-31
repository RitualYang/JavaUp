package com.base.lambda.exercise;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author wty
 * @date 2020/4/24 17:29
 */
public class Exercise3 {
    public static void main(String[] args) {
        //集合遍历
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //将集合中的每一个元素都带入到方法accept中。
        list.forEach(System.out::println);

        //输出集合中所有的偶数
        list.forEach(element -> {
            if (element % 2 == 0) {
                System.out.println(element);
            }
        });
    }
}
