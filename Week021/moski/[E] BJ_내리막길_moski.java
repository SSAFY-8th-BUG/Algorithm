package baekjoon.내리막길_1520;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

   static int M, N;
   
   static int[][] map;
   
   static int[][] dp;
   
   static boolean[][] visit;
   
   static int[] dy = {-1, 1, 0, 0};
   static int[] dx = {0, 0, -1, 1};
   
   public static void main(String[] args) throws Exception{
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      M = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      
      map = new int[M+1][N+1];
      dp = new int[M+1][N+1];
      
      visit = new boolean[M+1][N+1];
      
      for (int i = 1; i <= M; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= N; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      System.out.println(dfs(1,1));
      
   }
   
   static int dfs(int y, int x) {
      if(y == M && x == N) return 1;
      if(visit[y][x]) return dp[y][x];
      visit[y][x] = true;
      
      for (int d = 0; d < 4; d++) {
         int ny = y + dy[d];
         int nx = x + dx[d];
         
         if(ny <= 0 || nx <= 0 || ny > M || nx > N) continue;
         
         if(map[y][x] > map[ny][nx]) {
            dp[y][x] += dfs(ny,nx);
         }
      }
      
      return dp[y][x];
   }

}