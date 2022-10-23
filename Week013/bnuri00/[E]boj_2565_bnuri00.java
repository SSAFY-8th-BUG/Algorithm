package week013;

import java.io.*;
import java.util.*;

public class Eboj_2565_bnuri00 {
	static class Line{
		int l, r;
		Line(int l, int r){
			this.l = l;
			this.r = r;
		}
	}
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Line[] arr = new Line[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr, (o1, o2) -> o1.l-o2.l);
		
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i].r > arr[j].r && dp[i]<dp[j]+1) dp[i] = dp[j]+1;
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) max = Math.max(max, dp[i]);
		System.out.println(N-max);
	}

}
