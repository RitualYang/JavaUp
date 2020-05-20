package com.base.reflect;
import com.base.entity.Student;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author WTY
 * @create 2020-03-04 18:16
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        getInstance();
    }

    public static void getInstance() throws Exception{
        Class aClass = Class.forName("com.base.entity.Student");
        //初始化
        //无参初始化
        Constructor<Student> constructors = aClass.getConstructor();
        Student student = constructors.newInstance();
        //有参初始化
        Constructor<Student> constructors1 = aClass.getConstructor(String.class);
        Student student1 = constructors1.newInstance("哈哈哈");
        //方法调用
        //有参方法调用
        Method study = aClass.getMethod("study", String.class);
        study.invoke(student,"我是好人");
        //无参方法调用
        Method study1 = aClass.getMethod("study");
        study1.invoke(student);
        //获取类的属性,无法获取私有化的属性
        Field[] fields = aClass.getFields();
        System.out.println("获取到Student类的属性");
        for (Field field : fields) {
            System.out.print(" " + field.getName());
        }
        Field mobile = aClass.getDeclaredField("mobile");
        mobile.setAccessible(true);
        Object o = mobile.get(student);
        System.out.println(o);
        //修改属性值
        mobile.set(student,"key");
        Object o1 = mobile.get(student);
        System.out.println(o1);
        Field nums = aClass.getField("nums");
        Object o2 = nums.get(student);
        System.out.println(o2);

    }
}
