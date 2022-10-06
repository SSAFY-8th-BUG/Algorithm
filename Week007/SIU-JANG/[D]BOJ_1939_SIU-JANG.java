package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_중량제한_1939 {
	
	static int N, M, v, w, c, left = 1, right, ans, from, to, mid;
	static List<List<Edge>> vertex = new ArrayList<>();
	
	// dfs를 위한 checked 배열
	static boolean[] checked;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// vertex 초기화
		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			vertex.get(v).add(new Edge(w, c));
			vertex.get(w).add(new Edge(v, c));
			
			// 이분 탐색을 위해 right값을 찾는다.
			right = Math.max(right, c);
		}
		
		st = new StringTokenizer(br.readLine());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		
		// 이분 탐색으로 가능한 무게를 찾는다.
		while (left <= right) {
			mid = (left + right) / 2;
			ans = -1;
			
			checked = new boolean[N + 1];
			
			checked[from] = true;
			dfs(from, to);
			
			if (ans == -1) right = mid - 1;
			else left = mid + 1;
		}
		
		System.out.println(right);
	}
	
	static void dfs(int cur, int target) {
		if (cur == target) {
			ans = mid;
			return;
		}
		
		for (int i = 0; i < vertex.get(cur).size(); i++) {
			Edge ne = vertex.get(cur).get(i);
			
			if (!checked[ne.v] && mid <= ne.c) {
				checked[ne.v] = true;
				dfs(ne.v, target);
			}
		}
	}
	
	static class Edge{
		int v;
		int c;
		
		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
