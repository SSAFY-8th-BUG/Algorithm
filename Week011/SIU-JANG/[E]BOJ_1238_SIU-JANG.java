package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_파티_1238 {
	
	static int N, M, X, ans = Integer.MIN_VALUE;
	static List<List<Edge>> vertex = new ArrayList<>();
	static int[] cost;
	static boolean[] checked;
	
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		cost = new int[N + 1];
		checked = new boolean[N + 1];
		
		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			vertex.get(v).add(new Edge(w, c));
		}
		
		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		
		System.out.println(ans);
	}
	
	static void reset() {
		pq.clear();
		Arrays.fill(cost, Integer.MAX_VALUE);
		Arrays.fill(checked, false);
	}
	
	static void dijkstra(int i) {
		int sum = 0;
		
		reset();
		
		cost[i] = 0;
		pq.add(new Edge(i, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (e.c > cost[e.v] || checked[e.v]) continue;
			
			checked[e.v] = true;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
				}
			}
		}
		
		sum += cost[X];
		
		reset();
		
		cost[X] = 0;
		pq.add(new Edge(X, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (e.c > cost[e.v] || checked[e.v]) continue;
			
			checked[e.v] = true;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
				}
			}
		}
		
		sum += cost[i];
		
		ans = Math.max(ans, sum);
	}
	
	static class Edge {
		int v;
		int c;
		
		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
