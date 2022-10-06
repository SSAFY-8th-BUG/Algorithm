package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_극장좌석_2302 {
	
	static int N, M, ans = 1;
	static int[] dp = new int[41];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// dp
		// 1명 => 1가지
		// 2명 => (1,2), (2,1) => 2가지
		// 3명 => (1,2,3), (2,1,3), (1,3,2) => 3가지
		// 4명 => (1,2,3,4), (2,1,3,4), (1,2,4,3), (2,1,4,3), (1,3,2,4) => 5가지
		// dp[N] = dp[N - 2] + dp[N - 1]
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		// vip 좌석을 고려한다.
		int beforeVip = 0;
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(br.readLine());
			ans *= dp[temp - beforeVip - 1];
			beforeVip = temp;
		}
		
		ans *= dp[N - beforeVip];
		
		System.out.println(ans);
	}
}
