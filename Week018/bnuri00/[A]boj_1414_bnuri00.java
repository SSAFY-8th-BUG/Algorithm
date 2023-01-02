package Week018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class A_boj_1414_bnuri00 {
	static class Edge{
		int a, b, len;

		public Edge(int a, int b, int len) {
			super();
			this.a = a;
			this.b = b;
			this.len = len;
		}
	}
	static int[] parent;
	static int N, result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> o1.len - o2.len);
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				if(c == '0') continue;
				
				int len = 0;
				if(c < 'a') len += c - 'A' + 27;
				else len += c - 'a' + 1;
				if(i==j) {
					result += len;
					continue;
				}
				pq.add(new Edge(i, j, len));
			}
		}
		
		while(!pq.isEmpty() && !check()) {
			Edge e = pq.poll();
			
			if(!union(e.a, e.b)) result += e.len;
		}
		if(check()) {
			while(!pq.isEmpty()) {
				result += pq.poll().len;
			}		
		}else result = -1;
		
		System.out.println(result);
	}
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		
		parent[aRoot] = bRoot;
		return true;
	}
	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	private static boolean check() {
		int setIdx = find(0);
		for (int i = 1; i < N; i++) {
			if(setIdx != find(i)) return false;
		}
		return true;
	}
	
}
