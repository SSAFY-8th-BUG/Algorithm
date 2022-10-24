import java.io.*;
import java.util.*;
// 전깃줄 제거가 아니라 최대 설치 개수를 N 에서 뺌 (역발상)
// 최대 설치 개수는 dp, LIS 를 이용해 구함
public class boj_2565_tableMinPark {

    static int N, max;
    static int[][] w;
    static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        w = new int[N + 1][2];
        dp = new int[N + 1];

        StringTokenizer st;

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            w[i][0] = Integer.parseInt(st.nextToken());
            w[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(w, (w1, w2) -> w1[0] - w2[0]);

        max = 0;
        for (int i = 1; i <= N; i++){
            dp[i] = 1;
            for (int j = 1; j < i; j++){
                if (w[j][1] < w[i][1]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
        br.close();
    }
}
