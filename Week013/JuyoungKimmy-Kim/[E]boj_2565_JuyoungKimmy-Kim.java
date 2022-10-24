package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * A : 1,2,3,4,6,7,9,10
 * B : 8,2,9,1,4,6,7,10
 * 
 */
public class BOJ2565 {

	static int N, max;
	static Pair[] pair;
	static int [] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		N=Integer.parseInt(br.readLine());
		
		pair=new Pair[N];
		dp=new int[N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			pair[i]=new Pair (a,b);
		}
		Arrays.sort(pair, (p1, p2) -> p1.a-p2.a);
		
		dp[0]=1;
		max=1;

		for (int i=1; i<N; i++) {
			dp[i]=1;
			for (int j=0; j<i; j++) {
				if (pair[i].b>pair[j].b && dp[i]<dp[j]+1) {
					dp[i]=dp[j]+1;
					max=Math.max(dp[i], max);
				}
			}
		}
	
//		for (int i=0; i<N; i++)
//			System.out.println(dp[i]);
		System.out.println(N-max);
		
	}

	static class Pair {
		int a,b;
		Pair (int a, int b) {
			this.a=a;
			this.b=b;
		}
	}
}
