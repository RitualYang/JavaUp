package agency.proxy;

/**
 * @author wty
 * @create 2020-01-06 17:02
 */
public class Student implements Person {

    public void work(String work) {
        System.out.println("我的工作是 " + work);
    }

    public void introduce() {
        System.out.println("我是一个学生");
    }
}
