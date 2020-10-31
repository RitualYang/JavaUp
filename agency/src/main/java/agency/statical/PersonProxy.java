package agency.statical;

/**
 * @author wty
 * @create 2020-03-05 17:02
 */
public class PersonProxy implements Person {
    private Person person;

    public PersonProxy(Person person) {
        this.person = person;
    }

    @Override
    public void work(String work) {
        System.out.println("静态前置通知");
        person.work(work);
        System.out.println("静态后置通知");
    }

    @Override
    public void introduce() {
        System.out.println("静态前置通知");
        person.introduce();
        System.out.println("静态前置通知");
    }
}
