package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008 {

	static int T,N,min,max;
	static int[] op, arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			
			op=new int[4];
			arr=new int[N];
			min=Integer.MAX_VALUE;
			max=Integer.MIN_VALUE;
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<4; i++) {
				op[i]=Integer.parseInt(st.nextToken());
			}
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			dfs (0, arr[0]);
			System.out.println("#"+tc+" "+(max-min));
		}
	}
	
	private static void dfs (int depth, int sum) {
		if (depth==N-1) {
			max=Math.max(max, sum);
			min=Math.min(min, sum);
			
			return ;
		}
		
		for (int i=0; i<4; i++) {
			if (op[i]==0) continue;
			
			op[i]--;
			switch (i) {
			case 0: dfs (depth+1, sum + arr[depth+1]); break;
			case 1: dfs (depth+1, sum - arr[depth+1]); break;
			case 2: dfs (depth+1, sum * arr[depth+1]); break;
			case 3: dfs (depth+1, sum / arr[depth+1]); break;
			}
			op[i]++;
		}
	}
}
