import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)   
public class boj_2468_tableMinPark {
    static int[][] map;
    static boolean[][] v;
    static int N, max, count, answer;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[y][x]);
            }
        }

        answer = 0;
        for (int h = 0; h <= max; h++){
            count = 0;
            v = new boolean[N][N];
            for (int y = 0; y < N; y++){
                for (int x = 0; x < N; x++){
                    if (v[y][x]) continue;
                    if (map[y][x] <= h) continue;                    
                    dfs(y, x, h);
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
        br.close();
    }

    static void dfs(int y, int x, int h){

        for (int d = 0; d < 4; d++){
            int nextY = y + dy[d];
            int nextX = x + dx[d];

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
            if (v[nextY][nextX]) continue;
            if (map[nextY][nextX] <= h) continue;    
            
            v[nextY][nextX] = true;
            dfs(nextY, nextX, h);
        }
    }
}
