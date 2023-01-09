package Week019.bnuri00;

import java.io.*;
import java.util.*;

/*
 * 참고: boj 질문(totalCost int to long..)
 * */
public class A_boj_16398_bnuri00 {
	static class Flow{
		int a, b, cost;
		public Flow(int a, int b, int cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}	
	}
	static int N;
	static int[] parent;
	static PriorityQueue<Flow> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		long totalCost = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i >= j) continue;

				pq.add(new Flow(i, j, cost));
				
			}
		}
		
		while(!pq.isEmpty()) {
			Flow tmpFlow = pq.poll();
			if(union(tmpFlow.a, tmpFlow.b)) totalCost += tmpFlow.cost; 
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
