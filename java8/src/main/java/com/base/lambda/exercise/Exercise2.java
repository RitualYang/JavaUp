package com.base.lambda.exercise;

import com.base.lambda.data.Person;

import java.util.TreeSet;

/**
 * @author WTY
 * @Date 2020/4/24 17-14
 */
public class Exercise2 {
    // 集合排序:
    //TreeSet<>
    public static void main(String[] args) {
        //使用Lambda表达式来实现Comparator接口,并实例化一个set对象
        TreeSet<Person> set = new TreeSet<>((o1, o2) -> {
            if (o1.age >= o2.age) {
                return -1;
            } else {
                return 1;
            }
        });
        set.add(new Person("wty", 22));
        set.add(new Person("lxb", 29));
        set.add(new Person("qyl", 55));
        set.add(new Person("lmk", 14));
        set.add(new Person("gzh", 13));
        set.add(new Person("shf", 21));

        System.out.println(set);
    }
}
