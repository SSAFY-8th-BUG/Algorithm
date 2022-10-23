import java.io.*;
import java.util.*;

public class boj_9084_tableMinPark {

    static int T, N, M;
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            coin = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) coin[i] = Integer.parseInt(st.nextToken());

            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 0; i < N; i++){
                for (int j = coin[i]; j <= M; j++){
                    dp[j] += dp[j - coin[i]];
                }
            }
            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}