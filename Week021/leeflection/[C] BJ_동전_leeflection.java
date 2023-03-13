// 동전1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] coin, dp;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N];
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j >= coin[i])
                    dp[j] += dp[j - coin[i]];
            }
        }
        System.out.println(dp[K]);
    }
}