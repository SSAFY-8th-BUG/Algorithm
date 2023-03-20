import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] map = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 0;
        int rt = n-1;
        int ans1 = 0;
        int ans2 = 0;
        int min = Integer.MAX_VALUE;
        while(lt<rt){
            int sum = map[lt]+map[rt];
            int tmp = Math.abs(sum);
            if(tmp < min){
                min = tmp;
                ans1 = map[lt];
                ans2 = map[rt];
            }
            if(sum > 0) rt--;
            else lt++;
        }
        System.out.println(ans1 + " "+ans2);
    }
}