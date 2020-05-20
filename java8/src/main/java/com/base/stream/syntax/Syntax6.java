package com.base.stream.syntax;

import com.base.stream.data.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * 规约
 * @author WTY
 * @Date 2020/4/25 20:54
 */
public class Syntax6 {
    /**
     * reduce(T identity,BinaryOperator) / reduce(BinaryOperator))
     * 可以将流中元素反复结合起来,得到一个值
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
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        Double sumSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum)
                .get();
        System.out.println(sumSalary);
    }
}
