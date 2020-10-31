package com.base.stream.syntax;

import com.base.stream.data.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * 查找与匹配
 *
 * @author wty
 * @date 2020/4/25 20:54
 */
public class Syntax5 {
    /**
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
                new Employee("张三", 19, 3333.44, Employee.Status.BUSY),
                new Employee("李四", 49, 7777.44, Employee.Status.FREE),
                new Employee("王五", 23, 2222.11, Employee.Status.VOCATION),
                new Employee("赵六", 22, 10000.22, Employee.Status.FREE),
                new Employee("段七", 61, 66666.44, Employee.Status.VOCATION),
                new Employee("段七", 61, 66666.44, Employee.Status.VOCATION),
                new Employee("段七", 61, 66666.44, Employee.Status.BUSY)
        );
        boolean allMatch = employees.stream()
                .allMatch(employee -> employee.getStatus() == Employee.Status.FREE);
        System.out.println(allMatch);
        boolean anyMatch = employees.stream()
                .anyMatch(employee -> employee.getStatus() == Employee.Status.FREE);
        System.out.println(anyMatch);
        boolean noneMatch = employees.stream()
                .noneMatch(employee -> employee.getStatus() == Employee.Status.FREE);
        System.out.println(noneMatch);
        Employee employee = employees.stream()
                .findFirst().get();
        System.out.println(employee.toString());
        Employee employee1 = employees.parallelStream()
                .findAny().get();
        System.out.println(employee1.toString());
        long count = employees.stream()
                .count();
        System.out.println(count);
        Employee maxEmployee = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).get();
        Employee minEmployee = employees.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).get();
        System.out.println("max salary: " + maxEmployee + " min salary: " + minEmployee);
    }
}
