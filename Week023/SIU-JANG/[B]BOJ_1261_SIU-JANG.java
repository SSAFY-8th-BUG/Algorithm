package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_알고스팟_1261 {
	
	static int N, M;
	static int[][] map;
	
	static boolean[][] checked;
	static int[][] cost;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		String s = "";
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		pq.add(new Edge(0, 0, 0));
		
		cost = new int[M][N];
		checked = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		cost[0][0] = 0;
		
		dijkstra();
		
		System.out.println(cost[M - 1][N - 1]);
	}
	
	static void dijkstra() {
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.x][e.y] || e.c > cost[e.x][e.y]) continue;
			
			checked[e.x][e.y] = true; 
			
			for (int d = 0; d < 4; d++) {
				int nx = e.x + dx[d];
				int ny = e.y + dy[d];
				
				if (nx >= 0 && nx < M && ny >= 0 && ny < N && !checked[nx][ny] && map[nx][ny] == 1) {
					if (cost[nx][ny] > cost[e.x][e.y] + 1) {
						cost[nx][ny] = cost[e.x][e.y] + 1;
						pq.add(new Edge(nx, ny, cost[nx][ny]));
					}
				} else if (nx >= 0 && nx < M && ny >= 0 && ny < N && !checked[nx][ny] && map[nx][ny] == 0) {
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
