import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,K;
	static int[][] dp;
	static int[] coins;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		K = Integer.parseInt(stn.nextToken());
		coins = new int[N];
		for(int i=0; i<N; i++) {
			int coin = Integer.parseInt(br.readLine());
			coins[i]=coin;
			
		}
		
		dp = new int[N][K+1];
		for(int i=1; i<=K; i++) {
			if(i%coins[0] ==0) dp[0][i]=1;
		}
		
		for(int i=1; i<N; i++) {
			int coin = coins[i];
			for(int j=1; j<=K; j++) {
				int up= dp[i-1][j];
				int left = 0;
				if(j-coin>0) left=dp[i][j-coin];
				
				if(coin == j) dp[i][j] = up+left+1;
				else dp[i][j] = up+left;
			}
		}
		/*for(int y=0; y<N; y++) {
			System.out.println(Arrays.toString(dp[y]));
		}*/
		System.out.println(dp[N-1][K]);
	}
}
