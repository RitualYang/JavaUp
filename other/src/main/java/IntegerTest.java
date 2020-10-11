/**
 * @author WTY
 * @Date 2020/8/10 15:50
 * @Description: TODO
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer i = 100;// = Integer.valueOf(100);
        Integer j = 100;
        System.out.println(i.equals(j));
        // Integer 默认在使用 -128-127时 会使用缓存中的数据
        Integer max = Integer.MAX_VALUE;
        Integer min = Integer.MIN_VALUE;
        System.out.println(max + "   " + min);
        Integer k = 129;
        Integer f = 129;
        System.out.println(k.equals(f));
    }
}
