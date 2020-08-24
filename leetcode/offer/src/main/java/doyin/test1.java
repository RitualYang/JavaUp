package doyin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-15 19:02
 */
public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int i = Integer.valueOf(s);//用户类型数量
        HashMap<String,StringBuffer> map = new HashMap(i);
        for (int j = 0;j < i;j++){
            String t = sc.nextLine();
            String[] s1 = t.split(" ");
            if (!map.containsKey(s1[1])){
                map.put(s1[1],new StringBuffer(s1[0]));
            }else {
                map.put(s1[1],map.get(s1[1]).append(" " + s1[0]));
            }
        }
        for (String s1 : map.keySet()) {
            System.out.print(s1);
            String[] s2 = map.get(s1).toString().split(" ");
            Arrays.sort(s2);
            for (String s3 : s2) {
                System.out.print(" " +s3);
            }
            System.out.println();
        }
    }
}
