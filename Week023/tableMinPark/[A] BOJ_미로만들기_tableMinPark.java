import java.io.*;
import java.util.*;

public class BOJ_미로만들기_tableMinPark {
    static int N, answer;
    static int[][] map;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++){
            String[] input = br.readLine().split("");
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(input[x]);
            }
        }

        answer = Integer.MAX_VALUE;
        solve();

        System.out.println(answer);
        br.close();
    }

    static class P{
        int y;
        int x;
        int d;
        public P (int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    static void solve() {

        PriorityQueue<P> q = new PriorityQueue<>((P p1, P p2) -> p1.d - p2.d);
        boolean[][] v = new boolean[N][N];

        q.add(new P(0, 0, 0));
        v[0][0] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            if (now.y == N - 1 && now.x == N - 1) {
                answer = Math.min(answer, now.d);
            }

            for (int i = 0; i < 4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx]) continue;

                q.add(new P(ny, nx, now.d + (map[ny][nx] == 1 ? 0 : 1)));
                v[now.y][now.x] = true;
            }
        }
    }    
}
