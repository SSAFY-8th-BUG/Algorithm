import java.io.*;

public class boj_2631_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // LIS 사용
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 1;
        int[] dp = new int[N];
        for (int i = 0; i < N; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
        br.close();
    }
}
