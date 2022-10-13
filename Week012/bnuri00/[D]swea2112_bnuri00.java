package week012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 풀이시간: 2시간 20분
 * 
 * */

// 보호 필름
public class Dswea2112_bnuri00 {
	static int D, W, K;	// 두께D, 가로W, 합격기준K
	static int minInput;
	static int[][] input;	// 원본 배열
	
	static int[][] film;
	
	static int[] ARow;	// 0
	static int[] BRow;	// 1
	
	static int[] tgt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			input = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(check(input)) {
				System.out.println("#"+t+" "+0);
				continue;
			}
			
			// 쓸 배열들 초기 세팅
			film = new int[D][];
			for (int i = 0; i < D; i++) {
				film[i] = input[i];
			}
			
			ARow = new int[W];
			BRow = new int[W];
			Arrays.fill(ARow, 0);
			Arrays.fill(BRow, 1);
			
			// 약물 주입 최소의 최대는 K번
			minInput = K;
			
			// 중복순열 
			// 0: A 주입
			// 1: B 주입
			// 2: 주입X
			tgt = new int[D];
			perm(0, 0);
			
			System.out.println("#"+t+" "+minInput);
		}
	}

	private static void perm(int tgtIdx, int cnt) {
		if(cnt >= minInput) {
			resetMap();
			return;
		}
		
		if(tgtIdx==D) {
			//System.out.println(Arrays.toString(tgt));
			
			setFilm();
			//print();
			if(check(film)) {
				minInput = cnt;
			}
			resetMap();
			return;
		}
		
		// 0: A, 1: B, 2: 주입X
		for (int i = 0; i < 3; i++) {
			tgt[tgtIdx] = i;
			perm(tgtIdx+1, i != 2? cnt+1: cnt);
		}
		
	}
	static void print() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(film[i][j] +" " );
			}
			System.out.println();
		}
		System.out.println("************************");
	}
	
	private static void setFilm() {
		for (int i = 0; i < D; i++) {
			if(tgt[i]==0) film[i] = ARow;
			else if(tgt[i]==1) film[i] = BRow;
		}
		
	}

	static void resetMap() {
		for (int i = 0; i < D; i++) {
			film[i] = input[i];
		}
	}

	private static boolean check(int[][] firm) {
		for (int i = 0; i < W; i++) {
			int len = 1;
			int type = firm[0][i];
			for (int j = 0; j < D; j++) {
				if(j==0) continue;	// 맨앞은 이미 기록함
				if(len >= K) break;	// 해당 열 검사 통과
				
				if(type != firm[j][i]) {
					len = 1;
					type = firm[j][i];
					continue;
				}
				
				len++;
			}
			if(len < K) return false;	// 검사 통과 못함
		}
		return true;
	}

}
