package doyin;

import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-15 19:02
 */
public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int d = Integer.valueOf(s[0]);
        int w = Integer.valueOf(s[1]);

        String[] s2 = sc.nextLine().split(" ");//距离
        String[] s3 = sc.nextLine().split(" ");//距离
        int[] i2 = new int[s2.length];//距离
        for (int i = 0;i< s2.length;i++){
            i2[i] = Integer.valueOf(s2[i]);
        }
        int[] i3 = new int[s3.length];//积水数
        for (int i = 0;i< s3.length;i++){
            i3[i] = Integer.valueOf(s3[i]);
        }
        int k = 0;
        int t = 0;
        while (k < i3.length){
            if (w < d){
                if (w > i2[k]){
                    d = d - i3[k] - i3[k];
                    w = w - i2[k];
                    t++;
                } else {
                    t = 0;
                }
            }
            k++;
        }
        if (t == 0){
            System.out.println(-1);
        }else {
            System.out.println(t);
        }
    }
}
