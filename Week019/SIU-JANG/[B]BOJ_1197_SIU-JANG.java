package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_최소스패닝트리_1197 {
	
	static int V, E, cnt, ans;
	static Edge[] edges;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edges = new Edge[E];
		parent = new int[V + 1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(u, v, c);
		}
		
		Arrays.sort(edges, (e1, e2) -> (e1.c - e2.c));
		
		makeSet();
		
		for (int i = 0; i < E; i++) {
			Edge e = edges[i];
			
			if (union(e.u, e.v)) {
				cnt++;
				ans += e.c;
			}
			
			if (cnt == V - 1) break;
		}
		
		System.out.println(ans);
	}
	
	static void makeSet() {
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if (px == py) return false;
		
		if (px < py) parent[py] = px;
		else parent[px] = py;
		
		return true;
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
