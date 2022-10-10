package week011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1238_bnuri00 {
	static class Edge implements Comparable<Edge>{
		int to, time;

		public Edge(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time = o.time;
		}	
	}
	static int N, M ,X;
	static int INF = 9999999;
	
	static ArrayList<Edge>[] edges;
	static ArrayList<Edge>[] edgesReverse;  // 간선 to, from을 반대로 저장
	static int[] distGo;
	static int[] distBack;
	
 	public static void main(String[] args) throws Exception {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		
 		N = Integer.parseInt(st.nextToken());
 		M = Integer.parseInt(st.nextToken());
 		X = Integer.parseInt(st.nextToken());
 		X--;
 		
 		edges = new ArrayList[N];
 		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
 		
 		edgesReverse = new ArrayList[N];
 		for (int i = 0; i < N; i++) {
 			edgesReverse[i] = new ArrayList<>();
		}
 		
 		for (int i = 0; i < M; i++) {
 			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;	// 도시 0부터 시작하도록 만들기
			int v = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			
			edges[u].add(new Edge (v, t));
			edgesReverse[v].add(new Edge(u, t));
		}
 		
 		distGo = dijk(edgesReverse);	// 각 도시에서 X로 간다 (to, from 도시 반대로 입력받은 리스트 이용)
 		distBack = dijk(edges);	// X에서 각 도시로 돌아감
 		
 		//System.out.println(Arrays.toString(distGo));
 		//System.out.println(Arrays.toString(distBack));
 		
 		System.out.println(calcMaxTime());
 		
	}

	private static int calcMaxTime() {
		int maxTime = -1;
		for (int i = 0; i < N; i++) {
			int currTime = distGo[i] + distBack[i];
			
			maxTime = Math.max(maxTime, currTime);
		}
		return maxTime;
	}

	private static int[] dijk(List<Edge>[] edgeList) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(X);
		
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[X] = 0;
		
		while(!pq.isEmpty()) {
			//Edge edge = pq.poll();
			int from = pq.poll();
			
			for (Edge e : edgeList[from]) {		
				
				// 새로 계산한 값이 원래 값보다 작으면(효율적이면)
				// dist 갱신, 우선순위큐에 넣기
				if(dist[from] + e.time < dist[e.to]) {
					dist[e.to] = dist[from] + e.time;
					pq.add(e.to);
				}
			}
		}
		return dist;
	}
}
