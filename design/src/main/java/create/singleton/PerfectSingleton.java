package create.singleton;

/**
 * 多线程下懒汉式单例模式实现
 * 实现关键：
 *  1. volatile关键字：可见性，防止指令重排(创建实体类时)
 *  2. 双重判断
 *  3. 加锁
 *
 * @author wty
 * @date 2020/10/10 22:31
 */
public class PerfectSingleton {
    private static volatile PerfectSingleton instance = null;
    private PerfectSingleton() {}
    public static PerfectSingleton getInstance() {
        if(null == instance) {
            synchronized(PerfectSingleton.class) {
                if(null == instance) {
                    instance = new PerfectSingleton();
                }
            }
        }
        return instance;
    }
}
