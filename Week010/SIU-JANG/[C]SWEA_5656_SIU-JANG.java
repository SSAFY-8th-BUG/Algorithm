package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_벽돌깨기_5656 {
	
	static int T, N, W, H, count, ans = Integer.MAX_VALUE;
	static int[][] map, backup;
	static boolean[][] checked;
	
	static int[] tgt;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			backup = new int[H][W];
			tgt = new int[N];
			
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					backup[i][j] = map[i][j];
				}
			}
			
			ans = Integer.MAX_VALUE;
			
			dfs(0);
			
			if (ans == Integer.MAX_VALUE) {
				ans = 0;
			} 
			
			System.out.println("#" + test_case + " " + ans);
		}	
	}
	
	static void remove(int x, int y) {
		checked[x][y] = true;
		
		int len = map[x][y];
				
		for (int k = 1; k < len; k++) {
			// 좌
			if (y - k >= 0 && !checked[x][y - k]) {
				remove(x, y - k);
			}
			// 우
			if (y + k < W && !checked[x][y + k]) {
				remove(x, y + k);
			}
			// 상
			if (x - k >= 0 && !checked[x - k][y]) {
				remove(x - k, y);
			}
			// 하
			if (x + k < H && !checked[x + k][y]) {
				remove(x + k, y);
			}
		}
	}
	
	static void drop() {
		for (int i = H - 2; i >= 0; i--) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0 && map[i + 1][j] == 0) {
					for (int k = i + 1; k < H; k++) {
						if (map[k][j] != 0) {
							map[k - 1][j] = map[i][j];
							map[i][j] = 0;
							break;
						}
						
						if (k == H - 1) {
							map[k][j] = map[i][j];
							map[i][j] = 0;
						}
					}
				} 
			}
		}
	}
	
	static void dfs(int tgtIdx) {
		if (tgtIdx == N) {
			// complete code
			
			for (int i = 0; i < N; i++) {
				int y = tgt[i];
				count = 0;
				
				for (int j = 0; j < H; j++) {
					if (map[j][y] != 0) {
						int x = j;
						
						checked = new boolean[H][W];
						remove(x, y);
						
						for (int k = 0; k < H; k++) {
							for (int l = 0; l < W; l++) {
								if (checked[k][l]) map[k][l] = 0;
							}
						}
						
						drop();
						
						break;
					}
				}
			}
			
			for (int k = 0; k < H; k++) {
				for (int l = 0; l < W; l++) {
					if (map[k][l] != 0) count++;
				}
			}
			
			ans = Math.min(ans, count);
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = backup[i][j];
				}
			}
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			tgt[tgtIdx] = i;
			dfs(tgtIdx + 1);
		}
	}
}
