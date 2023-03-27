package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1261_알고스팟 {
    static int N,M;
    static int[][] dist;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j)-'0';
                dist[i][j] = Integer.MAX_VALUE/1000;
            }
        }

        dijkstra();
        System.out.println(dist[N-1][M-1]);
    }

    private static void dijkstra() {
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2)->{
            return p1[2] - p2[2];
        });
        pq.add(new int[]{0,0,0});
        while(!pq.isEmpty()){
            int tmp[] = pq.poll();
            for(int k=0; k<4; k++){
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];
                if(nx<0||ny<0||nx>=N||ny>=M) continue;
                if(map[nx][ny]==1){
                    if(dist[nx][ny] > tmp[2]+1){
                        dist[nx][ny] = tmp[2]+1;
                        pq.add(new int[] {nx,ny,tmp[2]+1});
                    }
                }else{
                    if(dist[nx][ny] > tmp[2]){
                        dist[nx][ny] = tmp[2];
                        pq.add(new int[]{nx,ny,tmp[2]});
                    }
                }
            }
        }
    }
}
