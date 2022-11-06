package week015;

import java.io.*;
import java.util.*;

// 호텔
public class A_boj_1106_bnuri00 {
	static int C, N;	// 늘려야하는 고객 수, 도시 개수
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][C+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= C; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				if(j==0) dp[i][j] = 0;
			}
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int cN = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= C; j++) {
				if(j-cN > 0) {
					dp[i][j] = Math.min(dp[i][j-cN], dp[i-1][j-cN])+cost;
				}
				dp[i][j] = Math.min(dp[i][j],dp[i-1][j]);
				int tmp = j/cN;
				if(j%cN != 0) tmp++;
				dp[i][j] = Math.min(dp[i][j], tmp*cost);
			}
		}
		System.out.println(dp[N][C]);
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= C; j++) {
//				if(dp[i][j] == Integer.MAX_VALUE) System.out.print("m ");
//				else System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

}
