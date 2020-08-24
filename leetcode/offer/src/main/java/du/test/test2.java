package du.test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-14 19:39
 */
public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        int n = Integer.valueOf(s1[0]);// 钞票种类
        int k = Integer.valueOf(s1[1]);// 工资数
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>(n);

        int rest = 0;//可发工资数
        for (int i = 0;i < n;i++){
            String s2 = sc.nextLine();
            String[] s3 = s2.split(" ");
            map.put(Integer.valueOf(s3[0]),Integer.valueOf(s3[1]));
        }

    }
}
