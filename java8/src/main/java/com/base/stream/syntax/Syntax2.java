package com.base.stream.syntax;

import com.base.stream.data.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 映射
 * @author WTY
 * @Date 2020/4/25 20:54
 */
public class Syntax2 {
    /**
     * map-接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数,该函数会被应用到每个元素中,并将其映射成一个新元素。
     * flatMap-接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流。
     */
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三",19,3333.44),
                new Employee("李四",49,7777.44),
                new Employee("王五",23,2222.11),
                new Employee("赵六",22,10000.22),
                new Employee("段七",61,66666.44),
                new Employee("段七",61,66666.44),
                new Employee("段七",61,66666.44)
        );
        employees.stream()
                .map(Employee::getName)
                .distinct()
                .forEach(System.out::println);

        employees.stream().
                flatMap(Syntax2::getWealthy)// 将多个流中的值,添加到一个新的流中
                .forEach(System.out::println);
    }
    public static Stream<String> getWealthy(Employee employee){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(employee.name);
        strings.add(String.valueOf(employee.age));
        strings.add(String.valueOf(employee.getSalary()));
        return strings.stream();
    }
}
