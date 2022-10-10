package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_나만안되는연애_14621 {
	
	static int N, M, ans;
	static char[] univ;
	
	static int[] parent;
	static Edge[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		univ = new char[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			univ[i] = st.nextToken().charAt(0);
		}
		
		edges = new Edge[M];
		parent = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			Edge edge = new Edge(u, v, c);
			
			edges[i] = edge;
		}
		
		Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);
		
		makeSet();
				
		int cnt = 0;
		for (int i = 0; i < edges.length; i++) {
			Edge edge = edges[i];
			
			int u = edge.v;
			int w = edge.w;
			int c = edge.c;
			
			if (univ[u] == univ[w]) continue;
			
			if (union(u, w)) {
				cnt++;
				ans += c;
			}
			
			if (cnt == N - 1) break;
		}
		
		if (cnt < N - 1) ans = -1;
				
		System.out.println(ans);
	}
	
	static class Edge {
		int v;
		int w;
		int c;
		
		public Edge(int v, int w, int c) {
			this.v = v;
			this.w = w;
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
