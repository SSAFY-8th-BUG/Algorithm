package boj;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_숨바꼭질3_13549 {
	
	static int N, K;
	
	// dijkstra
	static int[] costs;
	static boolean[] checked;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (e1.c - e2.c));
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		int len = Math.max(N, K);
		
		costs = new int[2 * len + 1];
		checked = new boolean[2 * len + 1];
		
		Arrays.fill(costs, Integer.MAX_VALUE);
		costs[N] = 0;
		
		pq.add(new Edge(N, 0));
		
		dijkstra();
		
		System.out.println(costs[K]);
	}
	
	static void dijkstra() {
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.u] || e.c > costs[e.u]) continue;
			
			checked[e.u] = true; 
			
			// 걷는 경우
			if (e.u + 1 <= K && !checked[e.u + 1] && costs[e.u + 1] > costs[e.u] + 1) {
				costs[e.u + 1] = costs[e.u] + 1;
				pq.add(new Edge(e.u + 1, costs[e.u + 1]));
			}
			
			if (e.u - 1 >= 0 && !checked[e.u - 1] && costs[e.u - 1] > costs[e.u] + 1) {
				costs[e.u - 1] = costs[e.u] + 1;
				pq.add(new Edge(e.u - 1, costs[e.u - 1]));
			}
			
			// 순간이동을 하는 경우
			if (e.u * 2 <= 2 * K && !checked[e.u * 2] && costs[e.u * 2] > costs[e.u]) {
				costs[e.u * 2] = costs[e.u];
				pq.add(new Edge(e.u * 2, costs[e.u * 2]));
			}
		}
	}
	
	static class Edge {
		int u;
		int c;
		
		public Edge(int u, int c) {
			this.u = u;
			this.c = c;
		}
	}
}
