package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_행성연결_16398 {
	
	static int N, E, cnt;
	static long ans;
	static Edge[] edge;
	
	// kruskal
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		E = (N * (N - 1)) / 2;
		
		parent = new int[N + 1];
		edge = new Edge[E];
				
		StringTokenizer st = null;
		int edgeIdx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				if (j <= i) continue;
				
				edge[edgeIdx++] = new Edge(i, j, next);
			}
		}
		
		Arrays.sort(edge, (e1, e2) -> e1.c - e2.c);
		
		makeSet();
		
		for (int i = 0; i < E; i++) {
			Edge e = edge[i];
			
			if (union(e.u, e.v)) {
				cnt++;
				ans += e.c;
			}
			
			if (cnt == N - 1) break;
		}
		
		System.out.println(ans);
	}
	
	static class Edge {
		int u;
		int v;
		int c;
		
		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
	
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
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
}
