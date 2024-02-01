import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_과제안내신분 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 30;

        int[] arr = new int[n+1];

        for (int i = 0; i < 28; i++) {
            int a = Integer.parseInt(br.readLine());
            arr[a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            if(arr[i] == 0){
                System.out.println(i);
            }
        }
    }
}
