package week011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sw2115_bnuri00 {
	static int N, M, C, maxProfit;	// 벌통 크기, 벌통 개수, 꿀 채취 최대양
	static int rowN;
	static int[][] honeyComb;
	static int[][] profitList;	// memoi
	
	static boolean[] tgt;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 사용한 자료 초기화
			maxProfit = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honeyComb = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeyComb[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rowN = N-M+1;
			profitList = new int[N][rowN];
//			for (int i = 0; i < pN; i++) {
//				for (int j = 0; j < pN; j++) {
//					profit[i][j] = calcProfit(i, j);
//				}
//			}
			tgt = new boolean[M];
			comb(0);
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < rowN; j++) {
//					System.out.print(profitList[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			visit = new boolean[N][rowN];
			dfs(0, 0, 0, 0);	// 0,0 부터 탐색 시작한다는 뜻
			//System.out.println(maxProfit);
			sb.append("#").append(t).append(" ").append(maxProfit).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int y, int x, int cnt, int profit) {

		if(cnt == 2) {
			maxProfit = Math.max(maxProfit, profit);
			return;
		}
		
		// 탐색 시작할 위치가 줄 넘어가면
		// 다음줄로 가야됨 
		if(x >= rowN) {
			x = 0;
			y++;
		}
		
		// 끝까지 탐색함
		if(y >= N) return;
		
		for (int i = y; i < N; i++) {
			for (int j = x; j < N-M+1; j++) {
				visit[i][j] = true;
				dfs(i, j+C, cnt+1, profit+profitList[i][j]);	// 겹치면 안되니까 C만큼 떨어뜨리기
				visit[i][j] = false;
				
			}
			x = 0;
		}
	}

	static void comb(int tgtIdx) {
		if(tgtIdx == M) {
			// complete code
			//System.out.println(Arrays.toString(tgt));
			
			// 현재 조합에 따라 이익 검사한 후 max 갱신 or 유지
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < rowN; j++) {
					profitList[i][j] = Math.max(profitList[i][j], calcProfit(i, j));
				}
			}
			return;
		}
		
		tgt[tgtIdx] = true;
		comb(tgtIdx+1);
		tgt[tgtIdx] = false;
		comb(tgtIdx+1);
	}

	private static int calcProfit(int y, int x) {
		int sum = 0;
		int profit = 0;
		for (int i = 0; i < M; i++) {
			if(tgt[i]) {
				sum += honeyComb[y][x+i];
				profit += Math.pow(honeyComb[y][x+i], 2);
			}
			if(sum > C) return -1;	// 채취할 수 있는 꿀 양 넘어갔음
		}
		return profit;
	}


}
