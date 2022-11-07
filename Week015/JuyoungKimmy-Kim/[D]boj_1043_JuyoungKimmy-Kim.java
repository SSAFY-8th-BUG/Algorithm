package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043 {

	static int ans;
	static int N, M, K; // 사람 수, 파티 수, 처음 진실을 알고 있는 사람 수
	static boolean[] know;
	static int[] parents;
	
	static List<Integer> party[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken()); // 사람 수
		M=Integer.parseInt(st.nextToken()); // 파티 수
		
		know=new boolean[N+1];
		
		party=new ArrayList[M];
		for (int i=0; i<M; i++)
			party[i]=new ArrayList<>();
		
		st=new StringTokenizer (br.readLine());
		int K=Integer.parseInt(st.nextToken());
		for (int i=0; i<K; i++) {
			know[Integer.parseInt(st.nextToken())]=true;
		}
		
		makeSet();
		
		int prev=0, now=0;
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int n=Integer.parseInt(st.nextToken());
			
			if (n>0) {
				prev=Integer.parseInt(st.nextToken());
				party[i].add(prev);
			}
			for (int j=1; j<n; j++) {
				now=Integer.parseInt(st.nextToken());
				party[i].add(now);
				
				union (prev, now);
				prev=now;		
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (know[i]) {
				know[find(i)]=true;
			}
		}
		
		for (int i=0; i<M; i++) {
			int p=find(party[i].get(0));
			if (!know[p]) ans++;
		}
		
		System.out.println(ans);

	}
	
	static void makeSet () {
		parents=new int[N+1];
		for (int i=1; i<=N; i++)
			parents[i]=i;
	}
	
	static int find (int u) {
		if (parents[u]==u)
			return parents[u]=u;
		else return find (parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u==v) return false;
		if (u>v) {
			parents[u]=v;
		} else
			parents[v]=u;
		
		return true;
	}

}
