package du.test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-14 19:39
 */
public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        int n = Integer.valueOf(s1[0]);//人数
        int k = Integer.valueOf(s1[1]);//饮料类型
        HashMap<String,Integer> map = new HashMap<String, Integer>(k);
        int rest = 0;
        String s2 = sc.nextLine();
        String[] s3 = s2.split(" ");
        for (String s4 : s3) {
            if (map.containsKey(s4)){
                map.remove(s4);
                rest++;
            }else{
                map.put(s4,1);
            }
        }
        rest = rest + map.size();
        System.out.println(rest);

    }
}
