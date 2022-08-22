package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Target {
	int x;
	int y;
	
	public Target(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_캐슬디펜스_17135 {
	
	static int N, M, D, ans;
	static int[][] map, backup;
	static int[] src;
	static int[] tgt = new int[3];
	static Target[] targetEnemy;
	
	public static void main(String[] args) throws Exception {
		//	입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		backup = new int[N][M];
		src = new int[M];
		for (int i = 0; i < M; i++) {
			src[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				backup[i][j] = map[i][j];
			}
		}
		//	궁수 배치(조합)
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) {
			// 궁수 배치 완료 시 시뮬레이션 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = backup[i][j];
				}
			}
			targetEnemy = new Target[3];
			ans = Math.max(ans, simulate());
			return;
		}
		
		if (srcIdx == src.length) {
			return;
		}
		
		tgt[tgtIdx] = src[srcIdx];
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static int simulate() {
		int stage = N;
		int ret = 0;
		while (stage-- > 0) {
//			1. 각 궁수가 사정 거리 안에 있는 적에게 1번 공격
			int dist1 = Integer.MAX_VALUE;
			int dist2 = Integer.MAX_VALUE;
			int dist3 = Integer.MAX_VALUE;
			
			int archer1 = tgt[0];
			int archer2 = tgt[1];
			int archer3 = tgt[2];
			
			for (int j = 0; j < M; j++) {
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] == 1) {
						int archerDist1 = getDistance(i, j, N, archer1);
						int archerDist2 = getDistance(i, j, N, archer2);
						int archerDist3 = getDistance(i, j, N, archer3);
						
						if (archerDist1 <= D && archerDist1 < dist1) {
							dist1 = archerDist1;
							targetEnemy[0] = new Target(i, j);
						}
						if (archerDist2 <= D && archerDist2 < dist2) {
							dist2 = archerDist2;
							targetEnemy[1] = new Target(i, j);
						}
						if (archerDist3 <= D && archerDist3 < dist3) {
							dist3 = archerDist3;
							targetEnemy[2] = new Target(i, j);
						}
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if (targetEnemy[i] != null && map[targetEnemy[i].x][targetEnemy[i].y] == 1) {
					map[targetEnemy[i].x][targetEnemy[i].y] = 0; 				
					ret++;
				}
			}
			//	2. 적이 모두 1칸씩 아래로 이동
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (i == N - 1) {
						map[i][j] = 0;
					} else {
						if (map[i][j] == 1) {
							map[i + 1][j] = 1;
							map[i][j] = 0;
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	static int getDistance(int x1, int y1, int x2, int y2) {
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}
}