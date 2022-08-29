package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_링크와스타트_15661 {
	
	static int N, ans = Integer.MAX_VALUE;
	static int[][] array;
	static int[] src;
	static int[] tgt;
	static int[] tgt2;
	
	public static void main(String[] args) throws Exception {
		//	입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		array = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		src = new int[N];
		for (int i = 0; i < N; i++) {
			src[i] = i;
		}
		for (int tgtSize = 1; tgtSize < N; tgtSize++) {
			tgt = new int[tgtSize];
			tgt2 = new int[N - tgtSize];
			comb(0, 0);
		}
		
		System.out.println(ans);
	}
	
	static void calc() {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < tgt.length; j++) {
				if (src[i] == tgt[j]) {
					flag = false;
					break;					
				}
			}
			
			if (flag) {
				if (idx < tgt2.length) tgt2[idx++] = src[i];
			}
		}
		
		int startTeamSum = 0;
		int linkTeamSum = 0;
		
		for (int i = 0; i < tgt.length; i++) {
			for (int j = 0; j < tgt.length; j++) {
				if (i == j) continue;
				
				startTeamSum += array[tgt[i]][tgt[j]];
			}
		}
		
		for (int i = 0; i < tgt2.length; i++) {
			for (int j = 0; j < tgt2.length; j++) {
				if (i == j) continue;
				
				linkTeamSum += array[tgt2[i]][tgt2[j]];
			}
		}
		
		ans = Math.min(ans, Math.abs(startTeamSum - linkTeamSum));
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == tgt.length) {
			calc();
						
			return;
		}
		
		if (srcIdx == N) {
			return;
		}
		
		tgt[tgtIdx] = src[srcIdx];
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
}
