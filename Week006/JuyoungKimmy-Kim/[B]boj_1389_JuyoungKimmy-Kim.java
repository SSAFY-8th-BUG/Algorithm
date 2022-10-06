package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1389 {

	static final int INF=987654321;
	
	static int N,M, ans=Integer.MAX_VALUE;
	static int [][] adj;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		adj=new int[N+1][N+1];
		for (int i=1; i<=N; i++)
			Arrays.fill(adj[i], INF);

		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			adj[A][B]=1;
			adj[B][A]=1;
		}
		System.out.println(floyd());
		
	}
	
	private static int floyd () {
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if (i==j) continue;
					if (adj[i][j]>adj[i][k]+adj[k][j])
						adj[i][j]=adj[i][k]+adj[k][j];
				}
			}
		}
		

		int minIdx=0;
		for (int i=1; i<=N; i++) {
			int sum=0;
			for (int j=1; j<=N; j++) {
				if (i==j) continue;
				sum+=adj[i][j];
			}
			if (sum<ans) {
				ans=sum;
				minIdx=i;
			}
		}
		return minIdx;
	}

}
