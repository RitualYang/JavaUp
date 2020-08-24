package doyin;

import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-15 19:02
 */
public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int d = Integer.valueOf(s[0]);
        int w = Integer.valueOf(s[1]);
        char[][] arr = new char[d][w];
        for (int i = 0;i < d;i++){
            String s1 = sc.nextLine();
             arr[i]=s1.toCharArray();
        }
        String s1 = sc.nextLine();
        int q = Integer.valueOf(s1);
        int[] qarr = new int[q];
        for (int i = 0;i<q;i++){
            String[] s2 = sc.nextLine().split(" ");
            int x1 = Integer.valueOf(s2[0]) - 1;
            int y1 = Integer.valueOf(s2[1]) - 1;
            int x2 = Integer.valueOf(s2[2]) - 1;
            int y2 = Integer.valueOf(s2[3]) - 1;
            if (x1 == x2 || y1 == y2){
                if (arr[x1][y1] == arr[x2][y2] && arr[x1][y1] != '.'&& arr[x2][y2] != '.'){
                    if (x1 == x2){
                        if (y1 > y2){
                            while (y1 > y2){
                                y1 = y1 - 1;
                                if (arr[x1][y1] != '.' && x1 != x2){
                                    qarr[i] = 0;
                                }else {
                                    qarr[i] = 1;
                                }
                            }
                        }else {
                            while (y1 < y2){
                                y2 = y2 - 1;
                                if (arr[x2][y2] != '.' && y2 != y1){
                                    qarr[i] = 0;
                                }else {
                                    qarr[i] = 1;
                                }
                            }
                        }
                    }else {
                        if (x1 > x2){
                            while (x1 > x2){
                                x1 = x1 - 1;
                                if (arr[x1][y1] != '.' && x1 != x2){
                                    qarr[i] = 0;
                                }else {
                                    qarr[i] = 1;
                                }
                            }
                        }else {
                            while (x1 < x2){
                                x2 = x2 - 1;
                                if (arr[x2][y2] != '.' && x2 != x1){
                                    qarr[i] = 0;
                                }else {
                                    qarr[i] = 1;
                                }
                            }
                        }
                    }
                }else {
                    qarr[i] = 0;
                }
            }else {
                qarr[i] = 0;
            }
        }
        for (int i1 : qarr) {
            if (i1 == 0){
                System.out.println("NO");
            }else {
                System.out.println("YES");
            }
        }
    }
}
