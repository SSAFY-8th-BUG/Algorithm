import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_7576_tableMinPark {

    static int N, M, answer;
    static int[][] map;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static Queue<P> q;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        q = new ArrayDeque<>();
        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 1) q.offer(new P(y, x, 0));
            }
        }

        answer = 0;
        bfs();

LOOP:   for (int y = 0; y < N; y++){
            for (int x = 0; x < M; x++){
                if (map[y][x] == 0) {
                    answer = -1;
                    break LOOP;
                }
            }
        }

        System.out.println(answer);
        br.close();
    }

    static void bfs(){
        while(!q.isEmpty()){
            P now = q.poll();
            answer = Math.max(answer, now.d);

            for (int d = 0; d < 4; d++){
                int nextY = now.y + dy[d];
                int nextX = now.x + dx[d];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                if (map[nextY][nextX] == 0){
                    q.add(new P(nextY, nextX, now.d + 1));
                    map[nextY][nextX] = 1;
                }
            }
        }
    }
}
