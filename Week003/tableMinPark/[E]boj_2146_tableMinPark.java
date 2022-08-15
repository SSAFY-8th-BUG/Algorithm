import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n^2)
// 공간복잡도 -> O(n^2)
public class boj_2146_tableMinPark {

    static int N, answer;
    static int[][] map;
    static boolean[][] v;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static class P{
        int y;
        int x;
        int d;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
        public P(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }

        @Override
        public boolean equals(Object obj) {
            return this.y == ((P) obj).y && this.x == ((P) obj).x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs로 섬끼리 묶어서 번호를 매김
        int land = 1;
        v = new boolean[N][N];
        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                if (map[y][x] == 0) continue;
                if (v[y][x]) continue;
                bfs(y, x, land++);
            }
        }

        answer = Integer.MAX_VALUE;
        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                v = new boolean[N][N];
                if (map[y][x] != 0) continue;   // 바다가 아니면 Pass

                // 바다 일 때 
                for (int i = 0; i < 4; i++){
                    int nextY = y + dy[i];
                    int nextX = x + dx[i];
    
                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
                    // 바다인데 주변에 땅이 하나라도 있으면 땅과 인접한 바다이기 때문에 다른 섬을 탐색하는 bfs
                    if (map[nextY][nextX] != 0) {
                        bfsBridge(y, x, map[nextY][nextX]);
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
    static void bfs(int y, int x, int land){
        Queue<P> q = new ArrayDeque<>();
        q.add(new P(y, x));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            map[now.y][now.x] = land;

            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
                if (v[nextY][nextX]) continue;
                if (map[nextY][nextX] == 0) continue;

                q.add(new P(nextY, nextX));
                v[nextY][nextX] = true;
            }
        }
    }

    static void bfsBridge(int y, int x, int land){
        Queue<P> q = new ArrayDeque<>();
        q.add(new P(y, x, 1));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            if (now.d > answer) continue;       // 시간많이걸리는 원인
            if (map[now.y][now.x] != land && map[now.y][now.x] != 0){
                answer = Math.min(answer, now.d - 1);
                continue;
            }

            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
                if (v[nextY][nextX]) continue;
                if (map[nextY][nextX] == land) continue;

                q.add(new P(nextY, nextX, now.d + 1));
                v[nextY][nextX] = true;
            }
        }
    }
}
