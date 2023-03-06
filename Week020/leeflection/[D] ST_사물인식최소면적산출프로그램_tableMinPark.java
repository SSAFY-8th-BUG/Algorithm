import java.io.*;
import java.util.*;

public class Main {
    static public class Pos{
        int x, y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, K;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Pos>> list = new ArrayList<>();
    public static void main(String args[])throws Exception
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       K = Integer.parseInt(st.nextToken());
       list = new ArrayList<>();
       for(int i=0; i<=K; i++){
           list.add(new ArrayList<Pos>());
       }
       for(int i=0; i<N; i++){
           st = new StringTokenizer(br.readLine());
           int x = Integer.parseInt(st.nextToken());
           int y = Integer.parseInt(st.nextToken());
           int idx = Integer.parseInt(st.nextToken());
           list.get(idx).add(new Pos(x,y));
       }
       dfs(0,1,1000,-1000,-1000,1000);
       System.out.println(ans);
    }

    static void dfs(int depth,int start,int x1, int y1, int x2, int y2){
        if(depth == K){
            int area = Math.abs(x2-x1) * Math.abs(y2-y1);
            ans = Math.min(ans,area);
            return;
        }
        for(Pos p : list.get(start)){
            if(Math.abs(Math.max(p.x,x2)-Math.min(p.x,x1))*Math.abs(Math.max(p.y,y1)-Math.min(p.y,y2)) >= ans) continue;
            dfs(depth+1,start+1,Math.min(p.x,x1),Math.max(p.y,y1),Math.max(p.x,x2),Math.min(p.y,y2));
        }
    }
}