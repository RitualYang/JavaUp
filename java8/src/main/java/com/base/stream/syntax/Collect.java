package com.base.stream.syntax;

import com.base.stream.data.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 收集
 *
 * @author wty
 * @date 2020/4/25 20:54
 */
public class Collect {
    /**
     * collect-将流转换为其他形式。接收一个Collector接口的实现,用于给Stream中元素做汇总的方法。
     */
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 19, 3333.44, Employee.Status.BUSY),
            new Employee("李四", 49, 7777.44, Employee.Status.FREE),
            new Employee("王五", 23, 2222.11, Employee.Status.VOCATION),
            new Employee("赵六", 22, 10000.22, Employee.Status.FREE),
            new Employee("段七", 61, 66666.44, Employee.Status.VOCATION),
            new Employee("段七", 61, 66666.44, Employee.Status.VOCATION),
            new Employee("段七", 61, 66666.44, Employee.Status.BUSY)
    );

    public static void main(String[] args) {

    }

    @Test
    public void toCollector() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("---------------------------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("---------------------------");
        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void count() {
        System.out.println("---------------------------");
        // 总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("---------------------------");
        // 平均值
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        System.out.println("---------------------------");
        // 总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("---------------------------");
        // 最大值
        Double maxS = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare))
                .get();
        System.out.println(maxS);
        System.out.println("---------------------------");
        // 最小值
        Double minS = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare))
                .get();
        System.out.println(minS);
        System.out.println("---------------------------");
    }

    @Test
    public void grouping() {
        // 分组
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        // 多级分组
        Map<Employee.Status, Map<String, List<Employee>>> map1 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 30) {
                        return "老年";
                    } else {
                        return "青少年";
                    }
                })));
        System.out.println(map1);
    }

    @Test
    public void partitioning() {
        // 分区
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(map);
    }

    @Test
    public void summarizing() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getCount());
        System.out.println(dss.getAverage());
        System.out.println(dss.getSum());
    }

    @Test
    public void joining() {
        // 连接
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "起始 ", " 结尾"));
        System.out.println(str);
    }

}
