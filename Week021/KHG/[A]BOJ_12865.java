import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main { 
	
	static int N,K;
	static Thing[] infos;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		K = Integer.parseInt(stn.nextToken());
		
		dp = new int[N+1][K+1];
		
		infos = new Thing[N+1];
		for(int i=1; i<=N; i++) {
			stn = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(stn.nextToken());
			int V = Integer.parseInt(stn.nextToken());
			infos[i] = new Thing(W,V);
		}
		

		
		for(int ki=1; ki<=K; ki++) {
			for(int ni=1; ni<=N; ni++) {
				if(ni ==0) {
					dp[ni][ki]=dp[N][ki-1];
				}else {
					if(infos[ni].w <= ki) {
						dp[ni][ki] = Math.max(dp[ni-1][ki], dp[ni-1][ki-infos[ni].w] + infos[ni].v);
					}else {
						dp[ni][ki] = dp[ni-1][ki];
					}
				}
				
				
			}
		}
		
		
		System.out.println(dp[N][K]);
		
		
	}	
	
	
	
	static class Thing{
		int w, v;
		Thing(int w, int v){
			this.w = w;
			this.v = v;
		}
		@Override
		public String toString() {
			return "Thing [w=" + w + ", v=" + v + "]";
		}
		
	}
}