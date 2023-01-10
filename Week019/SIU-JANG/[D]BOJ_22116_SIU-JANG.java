package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_창영이와퇴근_22116 {
	
	static int N;
	static int ans;
	static int[][] map;
	
	// dijkstra
	static int[][] cost;
	static boolean[][] checked;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((e1, e2) -> (e1.c - e2.c));
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cost = new int[N][N];
		checked = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		dijkstra();
		
		System.out.println(cost[N - 1][N - 1]);
	}
	
	static void dijkstra() {
		cost[0][0] = 0;
		pq.add(new Edge(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.u][e.v] || e.c > cost[e.u][e.v]) continue;
			
			checked[e.u][e.v] = true;
			
			for (int d = 0; d < 4; d++) {
				int nu = e.u + dx[d];
				int nv = e.v + dy[d];
				
				if (nu >= 0 && nu < N && nv >= 0 && nv < N && !checked[nu][nv]) {
					if (Math.abs(map[nu][nv] - map[e.u][e.v]) > cost[e.u][e.v]) {
						cost[nu][nv] = Math.abs(map[nu][nv] - map[e.u][e.v]);
					} else {
						cost[nu][nv] = Math.min(cost[nu][nv], cost[e.u][e.v]);
					}
					pq.add(new Edge(nu, nv, cost[nu][nv]));
				}
			}
		}
	}
	
	static class Edge {
		int u;
		int v;
		int c;
		
		public Edge (int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
}
