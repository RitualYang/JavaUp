package com.wty.jvm.classLoader;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author wty
 * @date 2020/12/14 14:59
 */
public class LoaderMain {
    public static void main(String[] args) {
        ClassLoader myClassLoader = LoaderMain.class.getClassLoader();
        ClassLoader myc = myClassLoader.getParent();
        ClassLoader mycParent = myc.getParent();
        System.out.println("自定义类的类加载器(应用类加载器)"+myClassLoader);
        System.out.println("自定义类的父加载器(平台类加载器)"+myc);
        System.out.println("自定义类的爷加载器"+mycParent);

        ClassLoader arrayListClassLoader = ArrayList.class.getClassLoader();
        System.out.println("arrayList的类加载器" + arrayListClassLoader);

        // Bootstrap ClassLoader 由C++编写 java中无该实体类
        ClassLoader objClassLoader = Object.class.getClassLoader();
        System.out.println("object的类加载器"+objClassLoader);
    }
}
