package week013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 풀이시간: 1시간 30분?
 * 참고: 블로그 코드
 * 
 * 
 * */
public class Bboj_9084_bnuri00 {
	static int N, M;
	static int[] cnt, coin;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			coin = new int[N];
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			cnt = new int[M+1];
			setCntArr();

			System.out.println(cnt[M]);
			//System.out.println(Arrays.toString(cnt));
		}
	}
	private static void setCntArr() {
		cnt[0] = 1;
		for (int i = 0; i < coin.length; i++) {
			for (int j = coin[i]; j <= M; j++) {
				cnt[j] += cnt[j-coin[i]];				
			}
		}
	}

}
