package week013;

import java.io.*;
import java.util.*;;

/*
 * 풀이시간: 1시간반+
 * 참고: EOF 관련 boj 질문 및 블로그 글
 * */
public class Cboj_3745_bnuri00 {
	static int N;
	static int arr[];
	static int dp[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strN = "";
		while((strN = br.readLine()) != null) {
			N = Integer.parseInt(strN.trim());
			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			dp = new int[N];
			Arrays.fill(dp,1);
			System.out.println(getIncreaseLen());
		}
		
	}
	private static int getIncreaseLen() {
		setDp();
		int maxLen = 0;
		for (int i = 0; i < N; i++) maxLen = Math.max(maxLen, dp[i]);
		return maxLen;
	}
	private static void setDp() {
		for (int i = 1; i < N; i++) {	// 현재 검사할 주가
			int max = 0;
			for (int j = 0; j < i; j++) {	// 이전 주가 중 더 작은거 중 길이(dp) max
				if(arr[j]<arr[i] && arr[j] > max) max = dp[j];
			}
			dp[i] = max+1;
		}
	}
}
