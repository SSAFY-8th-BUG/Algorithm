import java.util.*;
import java.io.*;


public class Main
{   
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static char[][] map;
    static boolean[][] visited;
    static int H,W;
    
    static int initDis;
    static StringBuilder sb;

    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for(int i=0; i<H; i++){
            map[i] = br.readLine().toCharArray();
        }
        loop:
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(map[i][j]=='#'){
                    int count = 0;
                    for(int k=0; k<4; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(nx<0||nx>=H||ny<0||ny>=W){
                            count++;
                            continue;
                        }
                        if(map[nx][ny]=='.') count++;
                        else{
                            initDis = k;
                        }
                    }
                    if(count == 3){
                        search(i,j);
                        break loop;
                    }
                }
            }
        }

    }
    public static void search(int i, int j){
        sb = new StringBuilder();
        Queue<int[]> q = new ArrayDeque<>();
        int dis = initDis;
        q.add(new int[]{i,j,dis});
        visited = new boolean[H][W];
        visited[i][j] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            for(int k=0; k<4; k++){
                int nx = tmp[0] + dx[k];
                int ny = tmp[1] + dy[k];
                if(nx<0||ny<0||nx>=H||ny>=W||visited[nx][ny]||map[nx][ny]=='.') continue;
                if(k==tmp[2]){
                    sb.append("A");
                    visited[nx][ny] = true;
                    visited[nx+dx[k]][ny+dy[k]] = true;
                    q.add(new int[]{nx+dx[k],ny+dy[k],tmp[2]});
                }
                else if((tmp[2]+1)%4 == k){
                    sb.append("R");
                    q.add(new int[]{tmp[0],tmp[1],k});
                }else if((tmp[2]+3)%4 == k){                   
                    sb.append("L");
                    q.add(new int[]{tmp[0],tmp[1],k});
                }
                
            }
        }
        System.out.println((i+1)+" "+(j+1));
        if(initDis==0){
            System.out.println("^");
        }else if(initDis==1){
            System.out.println(">");
        }else if(initDis==2){
            System.out.println("v");
        }else{
            System.out.println("<");
        }
        System.out.println(sb);
    }
}