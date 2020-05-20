package com.base.stream.syntax;

import com.base.stream.data.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * 终止操作
 * @author WTY
 * @Date 2020/4/25 20:54
 */
public class Syntax4 {
    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中任意元素
     * count-返回流中元素的总个数
     * max-返回流中最大值
     * min-返回流中最小值
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
                .sorted() // 需要实现Comparable
                .forEach(System.out::println);
        System.out.println("---------------------------------------");
        employees.stream()
                .sorted((e1,e2) -> {
                    if (e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}
