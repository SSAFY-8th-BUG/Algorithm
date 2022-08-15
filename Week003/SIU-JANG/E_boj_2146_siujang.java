package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Island {
	int x;
	int y;
	int dist;
	
	Island(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class BOJ_다리만들기_2146 {
	
	static int N, islandNo, ans;
	static int[][] map;
	static boolean[][] checked;
	static Queue<Island> q;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		ans = Integer.MAX_VALUE;
				
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		islandNo = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					dfs(i, j);
					islandNo++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int x, int y) {
		map[x][y] = islandNo;
		for (int d = 0; d < 4; d++) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			
			if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && map[nextX][nextY] == 1) {
				dfs(nextX, nextY);
			}
		}
	}
	
	static void bfs(int x, int y) {
		q = new ArrayDeque<>();
		q.add(new Island(x, y, 0));
		checked = new boolean[N][N];
		checked[x][y] = true;
		while (!q.isEmpty()) {
			Island current = q.poll();
			if (current.dist >= ans) {
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nextX = current.x + dx[d];
				int nextY = current.y + dy[d];
				
				if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && map[nextX][nextY] == 0 && !checked[nextX][nextY]) {
					checked[nextX][nextY] = true;
					q.add(new Island(nextX, nextY, current.dist + 1));
				} else if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && map[nextX][nextY] != map[x][y] && !checked[nextX][nextY]) {
					ans = current.dist;
				}
			}
		}
	}
}
