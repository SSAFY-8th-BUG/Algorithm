package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_백도어_17396 {
	
	static int N, M;
	static int[] seen;
	static List<List<Edge>> vertex = new ArrayList<>();
	
	// dijkstra
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (int)(e1.c - e2.c));
	static long[] cost;
	static boolean[] checked;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		seen = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seen[i] = Integer.parseInt(st.nextToken());
		}
		
		cost = new long[N];
		checked = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			vertex.add(new ArrayList<>());
		}
		
		int u, v, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			vertex.get(u).add(new Edge(v, c));
			vertex.get(v).add(new Edge(u, c));
		}
		
		Arrays.fill(cost, Long.MAX_VALUE);
		cost[0] = 0;
		
		dijkstra();
		
		if (cost[N - 1] == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(cost[N - 1]);			
		}
	}
	
	static void dijkstra() {
		pq.add(new Edge(0, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.v] || cost[e.v] < e.c) continue;
			
			checked[e.v] = true;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[ne.v] > cost[e.v] + ne.c && ne.v == N - 1) {
					cost[ne.v] = cost[e.v] + ne.c;
				}
				if (!checked[ne.v] && seen[ne.v] == 0 && cost[ne.v] > cost[e.v] + ne.c) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
				}
			} 
		}
	}
	
	static class Edge {
		int v;
		long c;
		
		public Edge (int v, long c) {
			this.v = v;
			this.c = c;
		}
	}
}
