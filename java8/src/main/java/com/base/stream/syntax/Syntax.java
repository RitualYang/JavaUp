package com.base.stream.syntax;

import com.base.stream.data.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * 筛选与切片
 *
 * @author wty
 * @date 2020/4/25 20:54
 */
public class Syntax {
    /**
     * filter-接受Lambda,从流中排除某些元素。
     * limit-截断流,使其元素不超过给定数量。
     * skip(n)-跳过元素,返回一个扔掉了前n个元素的流。若流中元素不足n个,则返回一个空流。与limit(n)互补。
     * distinct-筛选,通过流所生成元素的hashCode()和equals()去掉重复元素。
     */
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 19, 3333.44),
                new Employee("李四", 49, 7777.44),
                new Employee("王五", 23, 2222.11),
                new Employee("赵六", 22, 10000.22),
                new Employee("段七", 61, 66666.44),
                new Employee("段七", 61, 66666.44),
                new Employee("段七", 61, 66666.44)
        );
        employees.stream()
                .filter((e) -> e.getAge() > 20)
                .limit(2)
                .distinct()//需重写hashCode与equals
                .forEach(System.out::println);
        System.out.println("----------------------------------------");
        employees.stream()
                .filter((e) -> e.getAge() > 20)
                .skip(2)
                .distinct()//需重写hashCode与equals
                .forEach(System.out::println);
    }
}
