package Week020.bnuri00;

import java.io.*;
import java.util.StringTokenizer;

public class A_boj_14728_bnuri00 {
	static int N, T;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		dp = new int[2][T+1];
		int[] tmp;
		for (int i = 1; i <= N; i++) {
			tmp = dp[0];
			dp[0] = dp[1];
			dp[1] = tmp;
			
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());	// 각 단원 별 예상 공부 시간
			int S = Integer.parseInt(st.nextToken());	// 단원 문제의 배점
			for (int j = 0; j <= T; j++) {
				if(K <= j) dp[1][j] = Math.max(dp[0][j], dp[0][j-K]+S); 
				else dp[1][j] = dp[0][j];
			}
		}
		System.out.println(dp[1][T]);
		
	}

}
