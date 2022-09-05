import java.io.*;
import java.util.*;

public class boj_2636_tableMinPark{

    static int N, M;
    static int[][] map;
    static boolean[][] v;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int ch = 0;
        while(true){
            int temp = bfs();
            if (temp == 0) break;
            answer++;
            ch = temp;
        }

        System.out.println(answer);
        System.out.println(ch);
        br.close();
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int bfs(){
        int count = 0;

        Queue<P> q = new ArrayDeque<>();
        v = new boolean[N][M];

        q.add(new P(0, 0));
        v[0][0] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX]) continue;
               
                v[nextY][nextX] = true;

                if (map[nextY][nextX] == 1){
                    map[nextY][nextX] = 0;
                    count++;
                } else q.offer(new P(nextY, nextX));
            }
        }

        return count;
    }

    static class P{
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}