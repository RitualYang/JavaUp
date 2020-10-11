package create.singleton;

/**
 * 单例模式简单实现
 * 核心：
 *  1.私有化构造方法
 *  2.提供对象获取的接口
 * @author wty
 * @Date 2020/10/10 22:14
 */
public class Singleton {
    public static void main(String[] args) {
        LazySingleton lazyS1 = LazySingleton.getInstance();
        LazySingleton lazyS2 = LazySingleton.getInstance();
        System.out.println("两次懒汉式获取对象实例是否为同一个：" + (lazyS1 == lazyS2));
        StarveSingleton starveS1 = StarveSingleton.getInstance();
        StarveSingleton starveS2 = StarveSingleton.getInstance();
        System.out.println("两次饿汉式获取对象实例是否为同一个：" + (starveS1 == starveS2));
    }
}

/**
 * 懒汉式单例模式
 * 特点:
 *  1. 类加载时不实例化对象,只有在第一次调用获取函数时才创建对象
 *  2. 避免出现内存浪费的问题
 *  3. 多线程环境下不能保证对象的唯一性(解决方法 {@link PerfectSingleton})
 */
class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}
    public static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * 饿汉式单例模式
 * 特点：
 *  1. 线程安全
 *  2. 类加载较慢
 *  3. 类加载时完成初始化,获取对象的速度较快
 */
class StarveSingleton {
    private static StarveSingleton instance = new StarveSingleton();
    private StarveSingleton(){}
    public static StarveSingleton getInstance() {
        return instance;
    }
}