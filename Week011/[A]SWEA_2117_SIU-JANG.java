package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_홈방범서비스_2117 {
	
	static int TC, N, M, ans;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		//	입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 풀이
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					q.clear();
					checked = new boolean[N][N];
					bfs(i, j);
				}
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	static void bfs(int x, int y) {
		q.add(new Node(x, y));
		checked[x][y] = true;
		
		int houseCnt = 0;
		
		if (map[x][y] == 1) {
			houseCnt++;
		}
		
		int k = 1;
		while (!q.isEmpty()) {
			if (M * houseCnt >= cost(k)) {
				ans = Math.max(ans, houseCnt);
			}
			
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Node current = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < N && !checked[nx][ny]) {
						q.add(new Node(nx, ny));
						checked[nx][ny] = true;
						
						if (map[nx][ny] == 1) {
							houseCnt++;
						}
					}
				}
			}
			
			k++;
		}
	}
	
	static int cost(int k) {
		return k * k + (k - 1) * (k - 1);
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