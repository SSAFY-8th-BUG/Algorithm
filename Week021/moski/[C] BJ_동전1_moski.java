package baekjoon.동전1_2293;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

   static int n, k;
   static int[] coin;
   static int[][] dp;
   
   public static void main(String[] args) throws Exception{
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      
      coin = new int[n+1];
      dp = new int[n+1][k+1];
      
      for (int i = 1; i <= n; i++) {
         coin[i] = Integer.parseInt(br.readLine());
      }
      Arrays.sort(coin);
      
      for (int i = 1; i <= n; i++) {
         for (int j = 0; j <= k; j++) {
            if(j == 0) {
               dp[i][j] = 1;
               continue;
            }
            if(j < coin[i]) dp[i][j] = dp[i-1][j];
            else {
               dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]];
            }
         }
      }
      
      System.out.println(dp[n][k]);

   }

}