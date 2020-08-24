import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-12 19:02
 */
public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        int n = Integer.valueOf(s1[0]);
        int k = Integer.valueOf(s1[1]);
        int l = Integer.valueOf(s1[2]);
        int r = Integer.valueOf(s1[3]);
        int rest = 1;
        ArrayList<Integer> integers = new ArrayList<Integer>(n);
        int max = n * r;
        int min = n * l;
        for (int i = min;i<max;i++){
            if (i % k == 0){
                rest = rest * (r - l + 1);
            }
        }
        System.out.println(rest);
    }
}
