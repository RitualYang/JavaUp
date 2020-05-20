package agency.proxy.manage;

import agency.proxy.Person;
import agency.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WTY
 * @create 2020-01-06 17:04
 */
public class PersonAgency {

    public static void main(String[] args) {
        haveParameter("学习");
        noParameter();
    }

    public static void haveParameter(String work){
        final Student student = new Student();
        /**
         * jdk原生代理。
         *      特点:基于接口实现的动态代理。
         *      使用的类:Proxy
         * 如何创建代理对象
         *      使用Proxy类中的newProxyInstance方法
         *      要求:被代理的类最少实现一个接口,如果没有则不可以使用
         * newProxyInstance方法参数:
         *      ClassLoader:类加载器
         *          用来加载代理对象字节码,和被代理对象使用相同的类加载器。
         *      Class[]:字节码数组
         *          加载此类实现的所有接口,让代理对象和被代理对象有相同实现的方法。
         *      InvocationHandler:用于提供增强的代码
         *          编写代理方法主体,通常使用匿名内部类创建。
         */
        Person proxyStudent =(Person)Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 作用:执行被代理对象的任何接口方法都会经过该方法
             * @param proxy 代理对象的引用
             * @param method 当前执行的方法
             * @param args  当前执行方法所需的参数
             * @return      和被代理对象有相同的返回值
             * @throws Throwable
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //1.获取方法的执行参数
                String arg = (String) args[0];
                //2.判断当前方法是不是工作
                if ("work".equals(method.getName())){
                    System.out.println("开始代理了");
                    returnValue = method.invoke(student,arg);
                    System.out.println("代理结束了");
                }
                return returnValue;
            }
        });
        proxyStudent.work(work);
    }

    public static void noParameter(){
        final Student student = new Student();
        Person proxyStudent =(Person)Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //1.判断当前方法是不是工作
                if ("introduce".equals(method.getName())){
                    System.out.println("开始代理了");
                    returnValue = method.invoke(student,args);
                    System.out.println("代理结束了");
                }
                return returnValue;
            }
        });
        proxyStudent.introduce();
    }
}
