import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author wty
 * @create 2020-03-12 19:38
 */
public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String s2 = sc.nextLine();
        String[] s1 = s.split(" ");
        int n = Integer.valueOf(s1[0]);
        int k = Integer.valueOf(s1[1]);
        String[] s3 = s2.split(" ");
        int[] arr = new int[n];
        for (int i = 0;i<s3.length;i++){
            arr[i] = Integer.valueOf(s3[i]);
        }
        HashMap<Integer,Integer> map  =new HashMap<Integer, Integer>();
        for (int i = 0;i< arr.length;i++){
            arr[i] = arr[i] | k;
            if (!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else {
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        int index=0;
        int val = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            int key = next.getKey();
            int value = next.getValue();
            if(value > val){
                index = key;
                val = value;
            }
        }
        System.out.println(index);
    }
}
