package com.base.entity;

/**
 * @author wty
 * @create 2020-03-04 18:19
 */
public class Student {

    public static int nums = 100;
    public String name;
    public int age;
    private String mobile;

    public Student() {
        System.out.println("无参构造函数被调用");
    }

    public Student(String type) {
        System.out.println("有参构造函数被调用 +" + type);
    }

    private Student(String type, int age) {
        System.out.println("有参构造函数被调用 +" + type + " 多参数 " + age);
    }

    public void study() {
        System.out.println("study无参方法被调用");
    }

    public void study(String type) {
        System.out.println("study有参方法被调用 + " + type);
    }

    public static void getNums() {
        System.out.println("静态方法被调用 = " + nums);
    }
}
