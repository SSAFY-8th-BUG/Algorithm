import java.io.*;
import java.util.*;

public class BJ_평범한배낭_tableMinPark {
    static int N, K;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            arr[i][0] = W;
            arr[i][1] = V;
        }

        dp = new int[N + 1][K + 1];
        for (int n = 1; n <= N; n++){
            for (int w = 0; w <= K; w++){
                int next = w - arr[n][0];
                
                dp[n][w] = dp[n - 1][w];                
                if (next >= 0) dp[n][w] = Math.max(dp[n][w], dp[n - 1][next] + arr[n][1]);
            }
        }

        System.out.println(dp[N][K]);
        br.close();
    }
}