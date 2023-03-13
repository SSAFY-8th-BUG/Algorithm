import java.io.*;
import java.util.*;

// .....후...LCS
public class BJ_가장긴바이토닉부분수열_tableMinPark {
    static int N;
    static int[] arr, rarr, dp, rdp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        rarr = new int[N];
        for (int i = 0, j = N - 1; i < N; i++, j--){
            int n = Integer.parseInt(st.nextToken());
            arr[i] = rarr[j] = n;
        }

        dp = new int[N];
        rdp = new int[N];
        for (int i = 0; i < N; i++){
            dp[i] = rdp[i] = 1;
            for (int j = 0; j <= i; j++){
                if (arr[j] < arr[i]) {              
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (rarr[j] < rarr[i]){
                    rdp[i] = Math.max(rdp[i], rdp[j] + 1);
                }
            }
        }

        int answer = 0;
        for (int i = 0, j = N - 1; i < N; i++, j--){
            answer = Math.max(answer, dp[i] + rdp[j]);
        }

        System.out.println(answer - 1);
        br.close();
    }
}