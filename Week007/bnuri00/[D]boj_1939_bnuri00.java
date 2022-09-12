package week007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 이분탐색 안쓰면 시간초과남
 * 
 * 풀이시간: 3시간+
 * 참고: boj 반례, 이분탐색 코드
 * 
 * 56832KB	616ms
 * 
 * <풀이방식>
 * - bfs, 이분탐색
 * 
 * - 가능한 무게를 넣어서 이동 가능한지 확인
 * - 무게를 이분탐색으로...
 * 
 * 
 * */
public class Dboj_1939_중량제한 {
	static class Edge{
		int node;
		long limit;
		public Edge(int node, long limit) {
			super();
			this.node = node;
			this.limit = limit;
		}
		
	}
	static class Item{
		int node;
		long minLimit;
		public Item(int node, long minLimit) {
			super();
			this.node = node;
			this.minLimit = minLimit;
		}
		
	}
	static int N, M, f1, f2;
	static long maxW = 0, minW = Long.MAX_VALUE;
	
	static List<Edge>[] adj;
	static long[] dijk;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long limit = Long.parseLong(st.nextToken());
			
			if(limit > maxW) maxW = limit;
			if(limit < minW) minW = limit;
			
			adj[a].add(new Edge(b, limit));
			adj[b].add(new Edge(a, limit));
		}
		
		st = new StringTokenizer(br.readLine());
		f1 = Integer.parseInt(st.nextToken());
		f2 = Integer.parseInt(st.nextToken());
		
		long left = minW;
		long right = maxW;
		long mid = (minW+maxW) / 2;
		long result = 0;
		while(left <= right) {
			
			mid = (left+right) / 2;
			
			if(bfsBinary(mid)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid -1;
			}
		}
		System.out.println(result);
		
	}
	static void print() {
		System.out.println("***********");
		for (int i = 0; i < dijk.length; i++) {
			System.out.print(dijk[i] + " ");
		}
	}
	
	static boolean bfsBinary(long weight) {
		
		boolean[] visit = new boolean[N+1];
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(f1);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			List<Edge> list = adj[node];
			
			for (int i = 0; i < list.size(); i++) {
				Edge e = list.get(i);
				
				if(e.limit < weight) continue;
				if(e.node == f2) {
					return true;
				}
				if(visit[e.node]) continue;
					
				visit[e.node] = true;
				q.add(e.node);
				
				
			}
			
			
		}
		
		return false;
		
	}
	

//	static void makeDijk() {
//		
//		Queue<Item> q = new ArrayDeque<Item>();
//		q.add(new Item(f1, Integer.MAX_VALUE));
//		dijk[f1] = -1;
//		
//		while(!q.isEmpty()) {
//			Item item = q.poll();
//			
//			List<Edge> list = adj[item.node];
//			
//			for (int i = 0; i < list.size(); i++) {
//				Edge e = list.get(i);
//				long currLimit = Math.min(item.minLimit, e.limit);
//				
//				if(currLimit <= dijk[f2]) continue;
//				if(e.node == f2) {
//					dijk[f2] = Math.max(dijk[f2], currLimit);
//					continue;
//				}
//				
//				if(dijk[e.node] == 0) dijk[e.node] = currLimit;
//				else if(dijk[e.node] == -1) continue;
//				else if(dijk[e.node] >= currLimit) continue;
//					
//				dijk[e.node] = currLimit;
//				q.add(new Item(e.node, currLimit));
//				
//				
//			}
//			
//			
//		}
//		
//		
//	}

	
}

