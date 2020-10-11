package com.base.lambda.exercise;

import com.base.lambda.data.Person;

import java.util.ArrayList;

/**
 * @author WTY
 * @Date 2020/4/24 17:34
 */
public class Exercise4 {
    public static void main(String[] args) {
        // 需求:删除集合中满足条件的元素
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("wty", 22));
        list.add(new Person("lxb", 29));
        list.add(new Person("qyl", 55));
        list.add(new Person("lmk", 14));
        list.add(new Person("gzh", 13));
        list.add(new Person("shf", 21));
        list.removeIf(person -> {
            if (person.age < 30) {
                return true;
            }
            return false;
        });
        System.out.println(list);
    }
}
