package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_파티_1238 {
	
	static int N, M, X;
	static int answer = Integer.MIN_VALUE;
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
		
		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<>());
		}
		
		int u, v, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			vertex.get(u).add(new Edge(v, c));
		}
		
		cost = new int[N + 1];
		checked = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			Arrays.fill(cost, Integer.MAX_VALUE);
			Arrays.fill(checked, false);
			cost[i] = 0;
			
			int a = dijkstra(i, X);
			
			Arrays.fill(cost, Integer.MAX_VALUE);
			Arrays.fill(checked, false);
			cost[X] = 0;
			
			int b = dijkstra(X, i);
			
			if (answer < a + b) {
				answer = a + b;
			}
		}
		
		System.out.println(answer);
	}
	
	static int dijkstra(int index, int target) {
		pq.add(new Edge(index, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.v] || e.c > cost[e.v]) continue;
			
			checked[e.v] = true;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[ne.v] > cost[e.v] + ne.c) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
				}
			}
		}
		
		return cost[target];
	} 
	
	static class Edge {
		int v;
		int c;
		
		public Edge (int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
