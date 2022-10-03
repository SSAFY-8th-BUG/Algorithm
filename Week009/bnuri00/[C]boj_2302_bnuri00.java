package week009;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 풀이시간: 1시간+
 * 참고: boj 10844 쉬운계단수 예전에 푼 풀이 참고
 * 
 * 14112KB	136ms
 * 
 * <풀이>
 * - dp, memoi
 * 
 * - 이전까지 만들어진 순열에 숫자 하나 붙이는 방식
 * 
 * - 맨 끝의 수는 2개의 위치 또는 1개 위치에 둘 수 있다.
 * - 이전 수가 123 인 경우, 1234 또는 1243
 * - 이전 수가 132 인 경우, 1324 는 가능하지만 1342는 불가 (2가 가능한 위치 벗어남)
 *   
 * <삽질목록>
 * - dp[2]를 setDp에 써서 N이 1인 경우에 인덱스에러 남, 리턴문 추가했다
 * 
 * */
public class Cboj_2302_bnuri00 {
	
	static int[] dp;	// 좌석수에 따른 경우의 수 배열
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		setDp(N);	// dp 배열 계산
		
		int result = 1;
		
		int prev = 0;
		for (int i = 0; i < M; i++) {
			int vip = Integer.parseInt(br.readLine());
			
			int len = vip-prev-1;	// 이전 vip 좌석과 현재 vip 좌석 사이 거리
			result *= dp[len];
			
			prev = vip;
		}
		
		if(prev < N) result *= dp[N-prev];
		
		System.out.println(result);

	}
	
	static void setDp(int n) {
		dp = new int[n+1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		if(n == 1) return;
		

		int cntOneCase = 0;
		int cntTwoCase = 1;
		
		for (int i = 2; i <= n; i++) {
			dp[i] = cntTwoCase*2+cntOneCase;
			
			cntOneCase = cntTwoCase;
			cntTwoCase = dp[i]-cntOneCase;
			
		}
	}

}
