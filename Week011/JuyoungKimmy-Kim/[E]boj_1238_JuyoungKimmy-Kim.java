package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {
	
	static final int INF=987654321;
	
	static int N,M,X, ans, max;
	static List<Edge> adj[];
	static boolean[] visited;
	static int[] dist, ret;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		
		
		adj=new ArrayList[N+1];
		for (int i=0; i<=N; i++)
			adj[i]=new ArrayList<>();
		
		dist=new int[N+1];
		ret=new int[N+1];
		
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from =Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int dist=Integer.parseInt(st.nextToken());
			
			adj[from].add(new Edge (to, dist));
		}
		
		for (int i=1; i<=N; i++) {
			Arrays.fill(dist, INF);
			
			dijkstra(i);
			ret[i]=dist[X];
		}
		
		Arrays.fill(dist, INF);
		dijkstra(X);
		
		for (int i=1; i<=N; i++)
			ret[i]+=dist[i];
		
		Arrays.sort(ret);
		
		System.out.println(ret[N]);
		

	}
	
	static void dijkstra (int start) {
		PriorityQueue<Edge> pq=new PriorityQueue<>( (Edge e1, Edge e2) -> e1.d-e2.d );
		pq.add(new Edge (start, 0));
		dist[start]=0;
		
		while (!pq.isEmpty()) {
			Edge edge=pq.poll();
			int cur=edge.v;
			int curDist=edge.d;
			
			for (int i=0; i<adj[cur].size(); i++) {
				int next=adj[cur].get(i).v;
				int nextDist=adj[cur].get(i).d;
				
				if (dist[next]>curDist+nextDist) {
					dist[next]=curDist+nextDist;
					pq.add(new Edge (next, dist[next]));
				}
			}
		}
	}
	
	static class Edge {
		int v;
		int d;
		
		Edge (int v,  int d) {
			this.v=v;
			this.d=d;
		}
	}

}
