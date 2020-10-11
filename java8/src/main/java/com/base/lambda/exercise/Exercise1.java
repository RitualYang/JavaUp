package com.base.lambda.exercise;

import com.base.lambda.data.Person;

import java.util.ArrayList;

/**
 * @author WTY
 * @Date 2020/4/24 17-14
 */
public class Exercise1 {
    // 集合排序:
    //ArrayList<>
    public static void main(String[] args) {
        //需求: 已知在一个ArrayList中有若干个Person对象,将这些Person对象按照年龄进行降序排序。
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("wty", 22));
        list.add(new Person("lxb", 29));
        list.add(new Person("qyl", 55));
        list.add(new Person("lmk", 14));
        list.add(new Person("gzh", 13));
        list.add(new Person("shf", 21));
        // 排序
        list.sort((o1, o2) -> o2.age - o1.age);
        System.out.println(list);
    }
}
