package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ2665_미로만들기 {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE/1000;
            }
        }
        dijkstra();

        System.out.println(dist[N-1][N-1]);
    }

    private static void dijkstra() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0});
        dist[0][0] = 0;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            for(int i=0; i<4; i++){
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if(nx<0||ny<0||nx>=N||ny>=N) continue;
                if(map[nx][ny] == 0) {
                    if(dist[nx][ny] > dist[tmp[0]][tmp[1]] + 1) {
                        dist[nx][ny] =  dist[tmp[0]][tmp[1]] + 1;
                        q.offer(new int[]{nx,ny});
                    }
                } else {
                    if(dist[nx][ny] > dist[tmp[0]][tmp[1]]) {
                        dist[nx][ny] =  dist[tmp[0]][tmp[1]];
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
        }
    }
}
