package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14621 {
	
	static final int INF=987654321;
	
	static int N,M, min=Integer.MAX_VALUE, minIdx;
	static List<Edge> []adj;
	static char [] gender;
	static int [] distance, parents;
	
	static PriorityQueue<Edge> pq=new PriorityQueue<>( (Edge e1, Edge e2)-> e1.dist-e2.dist);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		adj=new ArrayList[N+1];
		for (int i=0; i<=N; i++)
			adj[i]=new ArrayList<>();
		
		gender=new char[N+1];
		distance=new int [N+1];
		Arrays.fill(distance, INF);
		
		parents=new int[N+1];
		for (int i=1; i<=N; i++)
			parents[i]=i;
		
		st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++)
			gender[i]=st.nextToken().charAt(0);
			
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			
			
			pq.add(new Edge (u, v,d));
		}

		int ret=mst();
		if (isAllConnected()) System.out.println(ret);
		else System.out.println(-1);
		
	}
	
	static int find (int u) {
		if (u==parents[u]) return u;
		else return find(parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u==v) return false;
		if (u>v) parents[u]=v;
		else parents[v]=u;
		
		return true;
	}
	
	static int mst () {
		int total=0;
		
		while (!pq.isEmpty()) {
			Edge edge=pq.poll();
			int u=edge.u;
			int v=edge.v;
			int dist=edge.dist;
			
			if (gender[u]==gender[v]) continue;
			
			if (union(u,v)) {
				total+=dist;
				adj[u].add(new Edge (u,v,dist));
				adj[v].add(new Edge (v,u,dist));
			}
		}
		return total;
	}
	
	static boolean isAllConnected () {
		
		if (adj[1].isEmpty()) return false;
		
		Queue<Integer> queue=new ArrayDeque<>();
		boolean[] visited=new boolean[N+1];
		visited[1]=true;
		
		for (int i=0; i<adj[1].size(); i++) 
			queue.add(adj[1].get(i).v);
		
		int cnt=1;
		while (!queue.isEmpty()) {
			int next=queue.poll();
			
			if (visited[next]) continue;
			visited[next]=true;
			cnt++;
			
			for (int i=0; i<adj[next].size(); i++)
				queue.add(adj[next].get(i).v);
		}
		
		if (cnt==N) return true;
		else return false;
		
	}
	
	static class Edge {
		int u, v,dist;

		public Edge(int u, int v, int dist) {
			super();
			this.u=u;
			this.v = v;
			this.dist = dist;
		}	
	}

}
