package softeer.로봇이지나간경로_1차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
   
   static int H,W;
    static char[][] map;
    static int start;
    
    static boolean[][] visit;
    static StringBuilder sb;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    
   public static void main(String[] args) throws Exception{
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      H = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      
      sb = new  StringBuilder();
      visit = new boolean[H][W];
      map = new char[H][W];
      
      for (int i = 0; i < H; i++) {
         map[i] = br.readLine().toCharArray();
      }
      
      Node n = findStart();
      
      dfs(n, start);
      
      System.out.println((n.y+1) + " " + (n.x+1));
      if(start == 0) System.out.println(">");
      if(start == 1) System.out.println("<");
      if(start == 2) System.out.println("v");
      if(start == 3) System.out.println("^");
      
      System.out.println(sb.toString());
      
      
      
   }
   
   static Node findStart() {
      Node n = null;
      for (int i = 0; i < H; i++) {
         for (int j = 0; j < W; j++) {
            
            if(map[i][j] == '#') {
               int cnt = 0;
               int dist = 0;
               for (int d = 0; d < dx.length; d++) {
                  int ny = i + dy[d];
                  int nx = j + dx[d];
                  
                  if(ny < 0 || nx < 0 || ny >= H || nx >= W) continue;
                  if(map[ny][nx] == '#') {
                     dist = d;
                     cnt++;
                  }
               }
               
               if(cnt == 1) {
                  n = new Node(i,j);
                  start = dist;
                  return n;
               }
            }
         }
      }
      
      return n;
      
   }
   
   static void dfs(Node n, int dist) {
      visit[n.y][n.x] = true;
      
      int ny = n.y + dy[dist];
      int nx = n.x + dx[dist];
      
      
      
      if(ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == '.'){
         for (int d = 0; d < dx.length; d++) {
            ny = n.y + dy[d];
            nx = n.x + dx[d];
            
            if(ny < 0 || nx < 0 || ny >= H || nx >= W || visit[ny][nx]) continue;
            
            if(map[ny][nx] == '#') {
               visit[ny][nx] = true;
               // 동(0)일때 왼쪽은 북(3) 오른쪽은 남(2)
               if(dist == 0) {
                  if(d == 3) sb.append("L");
                  else if(d==2) sb.append("R");
               }
               // 서(1)일때 왼쪽은 남(2) 오른쪽은 북(3)
               if(dist == 1) {
                  if(d == 2) sb.append("L");
                  else if(d==3) sb.append("R");
               }
               // 남(2)일때 왼쪽은 동(0) 오른쪽은 서(1)
               if(dist == 2) {
                  if(d == 0) sb.append("L");
                  else if(d==1) sb.append("R");
               }
               // 북(3)일때 왼쪽은 서(1) 오른쪽은 동(0)
               if(dist == 3) {
                  if(d == 1) sb.append("L");
                  else if(d==0) sb.append("R");
               }
               
               sb.append("A");
               ny += dy[d];
               nx += dx[d];
               dfs(new Node(ny, nx), d);
               
            }
            
         }
      }else{
         visit[ny][nx] = true;
         sb.append("A");
         if(dist == 0) nx++;
         else if(dist == 1) nx--;
         else if(dist == 2) ny++;
         else if(dist == 3) ny--;
         
         dfs(new Node(ny, nx), dist);
      }
      
      
   }
   
   static class Node{
      int y,x;
      public Node(int y, int x) {
         this.y = y;
         this.x = x;
      }
   }
   
   

}