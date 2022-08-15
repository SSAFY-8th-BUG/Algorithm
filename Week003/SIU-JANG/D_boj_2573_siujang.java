package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Glacier {
	int x;
	int y;
	
	Glacier(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_빙산_2573 {
	
	static int N, M;
	static int[][] map;
	static int[][] melt;
	static boolean[][] checked;
	static Queue<Glacier> q;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		while (true) {
			melt = new int[N][M];
			checked = new boolean[N][M];
			q = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						for (int d = 0; d < 4; d++) {
							int nextX = i + dx[d];
							int nextY = j + dy[d];
							
							if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] == 0) {
								melt[i][j]++;
							}
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] -= melt[i][j];
					if (map[i][j] < 0) {
						map[i][j] = 0;
					}
				}
			}
			cnt++;
			
			int partCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0 && !checked[i][j]) {
						checked[i][j] = true;
						q.add(new Glacier(i, j));
						bfs();
						partCnt++;
					}
				}
			}
			
			if (partCnt == 0) {
				System.out.println(0);
				break;
			}
			
			if (partCnt >= 2) {
				System.out.println(cnt);
				break;
			}
		}
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			Glacier current = q.poll();
			for (int d = 0; d < 4; d++) {
				int nextX = current.x + dx[d];
				int nextY = current.y + dy[d];
				
				if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !checked[nextX][nextY] && map[nextX][nextY] != 0) {
					checked[nextX][nextY] = true;
					q.add(new Glacier(nextX, nextY));
				}
			}
		}
	}
}
