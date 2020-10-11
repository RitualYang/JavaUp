package com.base.lambda.syntax;

import com.base.lambda.data.Person;

/**
 * @author WTY
 * @Date 2020/4/17-05
 */
public class Syntax4 {
    public static void main(String[] args) {
        PersonCreater creater = () -> new Person();
        // 无参构造方法的引用:
        PersonCreater creater1 = Person::new;
        Person a = creater1.getPersion();

        // 有参构造方法的引用:
        PersonCreater2 creater2 = Person::new;
        Person b = creater2.getPersion("wty", 22);
    }
}

//需求:
interface PersonCreater {
    Person getPersion();
}

interface PersonCreater2 {
    Person getPersion(String name, int age);
}
