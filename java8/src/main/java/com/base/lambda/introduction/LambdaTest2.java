package com.base.lambda.introduction;

/**
 * @author: WTY
 * @Date: 2020/4/23-22-23
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        // 1.使用接口实现类
        Comparator comparator1 = new MyComparator();

        // 2.使用匿名内部类
        Comparator comparator2 = new Comparator() {
            @Override
            public int compare(int a, int b) {
                return a + b;
            }
        };
        // 3.使用lambda表达式
        Comparator comparator3 = (a, b) -> a + b;

    }
}
class MyComparator implements Comparator{
    @Override
    public int compare(int a, int b) {
        return a + b;
    }
}
@FunctionalInterface//修饰函数式声明接口: 接口中的抽象方法只有一个。
interface Comparator {
    int compare(int a,int b);
}
