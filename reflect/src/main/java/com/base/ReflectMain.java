package com.base;

import com.base.entity.Student;

import java.lang.reflect.Modifier;

/**
 * TODO
 *
 * @author wty
 * @date 2020/12/15 22:36
 */
public class ReflectMain {
    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;
        className(studentClass);
        modifier(studentClass);
        getPackage(studentClass);
        //Class<? super Student> superclass = studentClass.getSuperclass();
        //Class<?>[] interfaces = studentClass.getInterfaces();
        //Field[] fields = studentClass.getFields();
        //Annotation[] annotations = studentClass.getAnnotations();
    }

    /**
     * 获取该的所在包的信息
     * @param studentClass
     */
    private static void getPackage(Class<Student> studentClass) {
        Package aPackage = studentClass.getPackage();
        System.out.println(aPackage.getName());
    }

    /**
     * 判断修饰符
     * @param studentClass
     */
    private static void modifier(Class<Student> studentClass) {
        int modifiers = studentClass.getModifiers();
        System.out.println(studentClass.getSimpleName()+ "是公共类？"+Modifier.isPublic(modifiers));
    }

    /**
     * 获取类名 + 全限定类名
     * @param studentClass
     */
    private static void className(Class<Student> studentClass) {
        // 类名
        String simpleName = studentClass.getSimpleName();
        // 全限定类名
        String name = studentClass.getName();
        System.out.println(simpleName + "-------" + name);
    }

}
