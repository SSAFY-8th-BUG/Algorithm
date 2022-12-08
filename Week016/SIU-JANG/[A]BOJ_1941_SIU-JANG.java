package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_소문난칠공주_1941 {
	
	static char[][] map = new char[5][];
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static Node[] src = new Node[25];
	static Node[] tgt = new Node[7];
	
	static int count = 0, ans;
	static boolean[][] checked;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int srcIdx = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				src[srcIdx++] = new Node(i, j);
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !checked[nx][ny] && possible(nx, ny)) {
				checked[nx][ny] = true;
				if (map[nx][ny] == 'Y') dfs(nx, ny);
				else {
					count++;
					dfs(nx, ny);
				}
			}
		}
	}
	
	static boolean possible(int nx, int ny) {
		for (int i = 0; i < 7; i++) {
			if (tgt[i].x == nx && tgt[i].y == ny) return true;
		}
		
		return false;
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 7) {
			// complete code
			checked = new boolean[5][5];
			checked[tgt[0].x][tgt[0].y] = true;
			count = 0;
			
			if (map[tgt[0].x][tgt[0].y] == 'S') count++;
			
			dfs(tgt[0].x, tgt[0].y);
			
			if (count >= 4) {
				int checkedCount = 0;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (checked[i][j]) checkedCount++;
					}
				}
				
				if (checkedCount == 7) ans++;
			}
			
			return;
		}
		
		if (srcIdx == 25) return;
		
		tgt[tgtIdx] = src[srcIdx];
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
