package week011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class boj14621_bnuri00 {
	static class Edge{
		int u, v, d;

		public Edge(int u, int v, int d) {
			super();
			this.u = u;
			this.v = v;
			this.d = d;
		}
		
	}
	
	static int N, M;
	static char[] typeList;
	//static ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((e1, e2) -> e1.d-e2.d);
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		typeList = new char[N];
		for (int i = 0; i < N; i++) {
			typeList[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;	// 학교 번호 0부터 시작하게 만들기
			int v = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			
			// 남초대학교끼리 혹은 여초대학교끼리 연결은 필요없음
			if(typeList[u] == typeList[v]) continue;
			
			pq.add(new Edge(u, v, d));
		}
		
		System.out.println(connect());

	}

	static int[] parent;
	private static int connect() {
		parent = new int[N];
		initParent();
		
		int len = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.u, e.v)) len += e.d;
		}
		
		if(!checkConnectAll()) return -1;
		
		return len;
	}
	
	static boolean checkConnectAll() {
		for (int i = 0; i < parent.length; i++) {
			for (int j = i+1; j < parent.length; j++) {
				int aRoot = find(i);
				int bRoot = find(j);
				if(aRoot!=bRoot) return false;
			}
		}
		return true;
	}
	static void initParent() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		
		if(aRoot < bRoot) parent[bRoot] = aRoot;
		else parent[aRoot] = bRoot;	
		return true;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;		
		return parent[a] = find(parent[a]);
	}

}
