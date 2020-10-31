package agency.statical;

/**
 * @author wty
 * @create 2020-03-05 17:04
 */
public class Test {
    public static void main(String[] args) {
        Person person = new PersonProxy(new Student());
        person.work("学习");
        person.introduce();
    }
}
