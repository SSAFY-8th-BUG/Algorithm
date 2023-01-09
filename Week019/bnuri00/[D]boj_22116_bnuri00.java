package Week019.bnuri00;

import java.io.*;
import java.util.*;

/*
 * 참고: boj 문제 분류, 질문
 * */
public class D_boj_22116_bnuri00 {
	static class Edge{
		int a, b, diff;

		public Edge(int a, int b, int diff) {
			super();
			this.a = a;
			this.b = b;
			this.diff = diff;
		}
	}
	static int N, result, INF = 1_000_000_000;
	static int[][] map;
	static int[] parent;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> o1.diff - o2.diff);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int pN = N*N;
		parent = new int[pN];
		for (int i = 0; i < pN; i++) {
			parent[i] = i;
		}
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		makeEdge();
		logic();
		System.out.println(result);
	}
	private static void logic() {
		while(!pq.isEmpty() && !check(0, N*N -1)) {
			Edge edge = pq.poll();
			result = Math.max(result, edge.diff);
			union(edge.a, edge.b);
		}
		
	}
	private static boolean check(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return true;
		return false;
	}
	private static void makeEdge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					if(ny < 0 || nx < 0|| ny >= N || nx >= N) continue;
					
					int diff = Math.abs(map[i][j] - map[ny][nx]);
					int a = i*N + j;
					int b = ny*N + nx;
					pq.add(new Edge(a, b, diff));
					
				}
			}
		}

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
