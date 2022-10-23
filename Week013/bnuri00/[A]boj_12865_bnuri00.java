package week013;

import java.io.*;
import java.util.StringTokenizer;

/*
 * 가치의 최댓값 구하기
 * 평범한 배낭*/
public class Aboj_12865_bnuri00 {
	static int N, K;	// N 물품 수, K 최대 무게
	static int[][] stuff;	// [i][0] -> i번째 물건 무게, [i][1]->i번째 물건 가치
	static int dp[][];		// [i][w]-> 0~i번째 물건을 고려했을 때, w무게 이하로 담은 최대 가치
	public static void main(String[] args) throws Exception {
		
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stuff = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1][K+1];
		makeDp();

		System.out.println(dp[N][K]);
	}
	private static void makeDp() {
		for (int i = 1; i <= N; i++) {	// 이번에 고려할 물건
			for (int j = 1; j <= K; j++) {
				if(j-stuff[i][0] <0) {
					dp[i][j] = dp[i-1][j];
					continue;
				}
				
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stuff[i][0]]+stuff[i][1]);
			}
		}
		
	}

}
