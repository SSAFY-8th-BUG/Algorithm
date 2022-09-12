package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * #1. 처음에 그냥 dfs로 했다가 계속 실패
 * #2. 중량은 0~1,000,000,000 중 하나가 답이므로 이진탐색을 해서 찾음
 */
public class BOJ1939 {

	static int N, M,start,end,ans;
	static List<Edge> edgeList[];
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		visited=new boolean[N+1];
		
		edgeList=new ArrayList[N+1];
		for (int i=0; i<N+1; i++)
			edgeList[i]=new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			edgeList[A].add(new Edge (B,C));
			edgeList[B].add(new Edge (A,C));
		}
		st=new StringTokenizer (br.readLine());
		start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
	
		int left=0, right=1_000_000_000;
		while (left<=right) {
			int mid=(left+right)/2;

			/*
			 * 최대 중량을 mid로 설정해두고, mid보다 더 큰 중량을 찾아서 도착지에 도착할 수 있으면
			 * true를, 아니면 false를 반환
			 */
			if (dfs (start, mid)) {
				ans=mid;
				left=mid+1;
			}
			else right=mid-1;
			
			Arrays.fill(visited, false);
		}
		
		System.out.println(ans);
	}
	

	private static boolean dfs (int v, int w) {
		visited[v]=true;
		if (v==end) return true;

		for (Edge e : edgeList[v]) {
			if (!visited[e.v] && e.w>=w) {
				if (dfs (e.v, w)) return true;
			}
		}
		return false;
	}
	
//	private static void dfs (int max, int u) {
//		
//		if (u==end) {
//			ans=Math.max(ans, max);
//			return ;
//		}
//		
//		for (int i=0; i<edgeList[u].size(); i++) {
//			int v=edgeList[u].get(i).v;
//			int w=edgeList[u].get(i).w;
//			
//			if (selected[v])continue;
//			selected[v]=true;			
//			dfs (Math.max(max, w), w);
//			selected[v]=false;
//		}
//	}
	
	static class Edge {
		int v;
		int w;
		
		Edge (int v, int w) {
			this.v=v;
			this.w=w;
		}
	}
}
