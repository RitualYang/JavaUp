import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-04 14:30
 */
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean[][] visited = new boolean[2][Integer.valueOf(s)];
        String s1 = sc.nextLine();
        char[] chars = s1.toCharArray();
        for (int i =0;i< chars.length;i++){
            if (chars[i] == '.'){
                visited[0][i] = true;
            }else {
                visited[0][i] = false;
            }
        }
        String s2 = sc.nextLine();
        char[] chars1 = s2.toCharArray();
        for (int i =0;i< chars.length;i++){
            if (chars1[i] == '.'){
                visited[1][i] = true;
            }else {
                visited[1][i] = false;
            }
        }
        System.out.println(dfs(0,0,visited));

    }
    public static int dfs(int i,int j,boolean[][] visited){
        if (i < 0 || i == 2 || j < 0 || j== visited[i].length || visited[i][j] == false) return 0;
        if (i == 1 && j == visited[i].length - 1 && visited[i][j] == true) return 1;
        return dfs(i+1,j+1,visited) + dfs(i,j+1,visited) + dfs(i - 1,j+ 1,visited);
    }


}
