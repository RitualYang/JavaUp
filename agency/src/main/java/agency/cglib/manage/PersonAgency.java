package agency.cglib.manage;

import agency.cglib.Student;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wty
 * @create 2020-01-06 17:04
 */
public class PersonAgency {

    public static void main(String[] args) {
        haveParameter("学习");
        noParameter();
    }

    /**
     * 有参方法代理
     * @param work
     */
    public static void haveParameter(String work) {
        final Student student = new Student();
        /**
         * cglib代理。
         *      特点:基于子类实现的动态代理。
         *      使用的类:Enhancer
         * 如何创建代理对象
         *      使用Enhancer类中的create方法
         *      要求:被代理的类不能是被final修饰的类。
         *      create方法参数:
         *      Class:类加载器
         *          指定被代理对象的字节码。
         *      Callback:用于提供增强的代码
         *          编写代理方法主体,通常使用匿名内部类创建。一般重写子类MethodInterceptor的匿名内部类实现。
         */
        Student CglibStudent = (Student) Enhancer.create(student.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param proxy 代理对象的引用
             * @param method 当前执行的方法
             * @param args  当前执行方法所需的参数
             * @param methodProxy 当前执行方法的代理对象
             * @return 和被代理对象有相同的返回值
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //1.获取方法的执行参数
                String arg = (String) args[0];
                //2.判断当前方法是不是工作
                if ("work".equals(method.getName())) {
                    System.out.println("开始代理了");
                    returnValue = method.invoke(student, arg);
                    System.out.println("代理结束了");
                }
                return returnValue;
            }
        });
        CglibStudent.work(work);
    }

    /**
     * 无参方法代理
     */
    public static void noParameter() {
        final Student student = new Student();
        Student CglibStudent = (Student) Enhancer.create(student.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //判断当前方法是不是介绍
                if ("introduce".equals(method.getName())) {
                    System.out.println("开始代理了");
                    returnValue = method.invoke(student, args);
                    System.out.println("代理结束了");
                }
                return returnValue;
            }
        });
        CglibStudent.introduce();
    }
}
