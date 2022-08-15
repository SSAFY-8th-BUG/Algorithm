package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Area {
	int x;
	int y;
	
	Area(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_안전영역_2468 {
	
	static int N, maxHeight, ans, cnt;
	static int[][] map;
	static boolean[][] checked;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	static Queue<Area> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st = null;
		maxHeight = 0;
		ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = maxHeight < map[i][j] ? map[i][j] : maxHeight;
			}
		}
		
		for (int i = 0; i <= maxHeight; i++) {
			checked = new boolean[N][N];
			q = new ArrayDeque<>();
			cnt = 0;
			
			for (int j = 0; j < N ; j++) {
				for (int k = 0; k < N; k++) {
					if (!checked[j][k] && map[j][k] > i) {
						bfs(new Area(j, k), i);
						cnt++;
					}
				}
			}			
			ans = cnt > ans ? cnt : ans;
		}
		
		System.out.println(ans);
	}
	
	static void bfs(Area area, int waterLevel) {
		q.add(area);
		checked[area.x][area.y] = true;
		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Area current = q.poll();
				for (int j = 0; j < 4; j++) {
					int nextX = current.x + dx[j];
					int nextY = current.y + dy[j];
					
					if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !checked[nextX][nextY] && map[nextX][nextY] > waterLevel) {
						checked[nextX][nextY] = true;
						q.add(new Area(nextX, nextY));
					}
				}
			}
		}
	}
}
