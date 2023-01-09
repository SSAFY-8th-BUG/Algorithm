package Week019.bnuri00;

import java.io.*;
import java.util.*;

public class B_boj_1197_bnuri00 {
	static class Edge{
		int a, b, cost;
		public Edge(int a, int b, int cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}	
	}
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V+1];
		for (int i = 0; i <= V; i++) {
			parent[i] = i;
		}
		
		long totalCost = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Edge(
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));		
		}
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.a, e.b)) totalCost += e.cost; 
		}
		System.out.println(totalCost);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}

	private static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	

}
