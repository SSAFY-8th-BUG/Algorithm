import java.io.*;

public class boj_2302_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // N = 1 -> 1                                   1가지
        // N = 2 -> 1, 2 / 2, 1                         2가지
        // N = 3 -> 1, 2, 3 / 1, 3, 2 / 2, 1, 3         3가지
        // N = 4 -> 1234 / 1243 / 1324 / 2134 / 2143    5가지

        // dp[N] = dp[N - 2] + dp[N - 1]
        int[] dp = new int[N + 2];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        int now = 0;
        int answer = 1;
        for (int i = 0; i < M; i++){
            int next = Integer.parseInt(br.readLine());
            answer *= dp[next - now - 1];
            now = next;
        }
        answer *= dp[N - now];          // 마지막꺼 안해줌 ㅠ
        
        System.out.println(answer);
        br.close();
    }
}
