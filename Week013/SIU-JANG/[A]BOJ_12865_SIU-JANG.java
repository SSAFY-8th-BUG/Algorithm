package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_평범한배낭_12865 { 
	
	static int N, K;
	static int[][] dp, item;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][K + 1];
		item = new int[N + 1][2]; // 0 => 무게, 1 => 가치
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			item[i][0] = W;
			item[i][1] = V;
		}
		
		for (int k = 1; k <= K; k++) { // 무게
			for (int n = 1; n <= N; n++) { // 개수
				dp[n][k] = dp[n - 1][k];
				
				if  (k - item[n][0] >= 0) dp[n][k] = Math.max(dp[n - 1][k], item[n][1] + dp[n - 1][k - item[n][0]]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
