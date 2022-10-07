import java.io.*;
import java.util.*;

public class swea_2117_tableMinPark {

    static int T, N, M, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int y = 0; y < N; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int y = 0; y < N; y++){
                for (int x = 0; x < N; x++){
                    search(y, x);
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static class P {
        int y;
        int x;
        int k;
        public P(int y, int x, int k){
            this.y = y;
            this.x = x;
            this.k = k;
        }
    }

    static void search(int y, int x){
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];

        q.add(new P(y, x, 1));
        v[y][x] = true;

        int land = 0;
        int home = 0;
        int cost = 1;
        int interval = 4;

        while(land < N * N){
            int size = q.size();

            for (int j = 0; j < size; j++){
                P now = q.poll();

                land++;
                if (map[now.y][now.x] == 1) home++;

                for (int i = 0; i < 4; i++){
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx]) continue;
                    
                    q.add(new P(ny, nx, now.k + 1));
                    v[ny][nx] = true;
                }
            }
            if (home * M - cost >= 0) answer = Math.max(answer, home);
            cost += interval;
            interval += 4;
        }        
    }
}