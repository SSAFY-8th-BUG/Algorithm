package week013;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// LCS
public class Dboj_9251_bnuri00 {
	static int R ,C;
	static char[] arr1, arr2;
	static int[] pass = new int['Z'-'A'+1];
	static int[][] dp;
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr1 = br.readLine().toCharArray();
		arr2 = br.readLine().toCharArray();
		
		R = arr1.length;
		C = arr2.length;
		dp = new int[R+1][C];
		
		setDp();
		print();
		System.out.println(dp[R][C-1]);
	}
	private static void setDp() {
		for (int i = 1; i <= R; i++) {	// arr1

			for (int j = 0; j < C; j++) {	// arr2
				if(j==0) {
					if(arr1[i-1] != arr2[j]) dp[i][j] = dp[i-1][j];
					else {
						
						dp[i][j] = 1;
					}
				} else if(arr1[i-1] != arr2[j]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				} else {
					if(dp[i-1][j-1]==dp[i-1][j]) {
						dp[i][j] = Math.max(dp[i-1][j]+1, dp[i][j-1]);
					}
					else {
					
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				    }
					
					
				}
			}
			
		}
		
	}
	static void print() {
		for (int i = 0; i < R+1; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
