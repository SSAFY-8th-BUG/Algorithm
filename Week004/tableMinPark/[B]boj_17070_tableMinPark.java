import java.io.*;
import java.util.StringTokenizer;

// 시간복잡도 -> O(n^2)
// 공간복잡도 -> O(n^2)
public class boj_17070_tableMinPark {

    // 하 - 대각 - 우
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 0, 1};
    static int[][] map;
    static boolean[][] v;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        
        map = new int[N][N];
        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N][N];
        v[0][0] = true;
        dfs(0, 1, 0);

        System.out.println(answer);
        br.close();
    }

    static void dfs(int y, int x, int d){
        if (y == N - 1 && x == N - 1){
            answer++;
            return;
        }

        boolean isWall = false;
        // 우 - 하 - 대각
        for (int j = 0; j < 3; j++){
            int nextY = y + dy[j];
            int nextX = x + dx[j];

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || v[nextY][nextX]) continue;
            if (map[nextY][nextX] == 1){
                isWall = true;
                continue;
            }
            if (d == 0 && j == 1) continue;
            if (d == 1 && j == 0) continue;

            v[nextY][nextX] = true;
            if (j == 2 && !isWall) dfs(nextY, nextX, j);
            else if (j < 2) dfs(nextY, nextX, j);
            v[nextY][nextX] = false;            
        }
    }
}
