import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_2573_tableMinPark {

    static int N, M, answer;
    static int[][] map;
    static boolean[][] v;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

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
        while(true){

            // 주변에 0 이 있는 지 확인해서 몇개 있는지 temp 배열에 저장
            int[][] temp = new int[N][M];
            for (int y = 0; y < N; y++){
                for (int x = 0; x < M; x++){
                    if (map[y][x] == 0) continue;
                    
                    int count = 0;
                    for (int d = 0; d < 4; d++){
                        int nextY = y + dy[d];
                        int nextX = x + dx[d];
                        if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                        if (map[nextY][nextX] == 0) count++;
                    }
                    temp[y][x] = count;
                }
            }

            // 주변에 0의 갯수만큼 빼준다.
            for (int y = 0; y < N; y++){
                for (int x = 0; x < M; x++){
                    if (temp[y][x] == 0) continue;
                    map[y][x] = map[y][x] - temp[y][x] < 0 ? 0 : map[y][x] - temp[y][x];
                }
            }

            int isLand = 0;
            v = new boolean[N][M];
            for (int y = 0; y < N; y++){
                for (int x = 0; x < M; x++){
                    if (map[y][x] == 0) continue;
                    if (v[y][x]) continue;
                    v[y][x] = true;
                    dfs(y, x);
                    isLand++;
                }
            }
            answer++;

            if (isLand >= 2) break;
            else if (isLand == 0){
                answer = 0;
                break;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static void dfs(int y, int x){
        for (int d = 0; d < 4; d++){
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
            if (v[nextY][nextX]) continue;
            if (map[nextY][nextX] == 0) continue;
            v[nextY][nextX] = true;
            dfs(nextY, nextX);
        }
    }
}
