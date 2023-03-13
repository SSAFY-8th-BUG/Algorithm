package baekjoon.가장긴바이토닉부분수열_11054;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main {

   static int N, ans;
   
   static int[] arr;
   static int[] dp_inc;
   static int[] dp_des;
   
   public static void main(String[] args) throws Exception{
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      ans = 0;
      arr = new int[N+1];
      
      dp_inc = new int[N+1];
      dp_des = new int[N+1];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
         arr[i] = Integer.parseInt(st.nextToken());
      }
      
      
      for (int i = 1; i <= N; i++) {
         dp_inc[i] = 1;
         for (int j = 1; j <= N; j++) {
            if(arr[j] < arr[i]) dp_inc[i] = Math.max(dp_inc[i], dp_inc[j]+1);
         }
      }
      
      for (int i = N; i > 0; i--) {
         dp_des[i] = 1;
         for (int j = N; j > 0; j--) {
            if(arr[j] < arr[i]) dp_des[i] = Math.max(dp_des[i], dp_des[j]+1);
         }
      }
      
      for (int i = 1; i <= N; i++) {
         ans = Math.max(ans, dp_des[i] + dp_inc[i]);
      }
      
      System.out.println(ans-1);
      

   }

}