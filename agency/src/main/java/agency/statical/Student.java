package agency.statical;

/**
 * @author wty
 * @create 2020-01-06 17:02
 */
public class Student implements Person {

    @Override
    public void work(String work) {
        System.out.println("我的工作是 " + work);
    }

    @Override
    public void introduce() {
        System.out.println("我是一个学生");
    }
}
