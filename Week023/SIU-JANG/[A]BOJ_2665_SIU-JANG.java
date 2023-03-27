package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_미로만들기_2665 {
	
	static int n;
	static int[][] map;
	
	// dijkstra
	static boolean[][] checked;
	static int[][] cost;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		String s = "";
		for (int i = 0; i < n; i++) {
			s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		checked = new boolean[n][n];
		cost = new int[n][n];		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		cost[0][0] = 0;
		pq.add(new Edge(0, 0, 0));
		
		dijkstra();
		
		System.out.println(cost[n - 1][n - 1]);
	}
	
	static void dijkstra() {
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.x][e.y] || cost[e.x][e.y] < e.c) continue;
			
			checked[e.x][e.y] = true;
			
			for (int d = 0; d < 4; d++) {
				int nx = e.x + dx[d];
				int ny = e.y + dy[d];
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0 && !checked[nx][ny]) {
					if (cost[nx][ny] > cost[e.x][e.y] + 1) {
						cost[nx][ny] = cost[e.x][e.y] + 1;
						pq.add(new Edge(nx, ny, cost[nx][ny]));
					}
				} else if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 1 && !checked[nx][ny]) {
					cost[nx][ny] = cost[e.x][e.y];
					pq.add(new Edge(nx, ny, cost[nx][ny]));
				}
			}
		}
	}
	
	static class Edge {
		int x;
		int y;
		int c;
		
		public Edge (int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		} 
	}
}
