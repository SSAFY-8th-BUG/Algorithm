import java.io.*;
import java.util.*;

public class swea_5656_tableMinPark {

    // 중복순열
    static int T, N, W, H, answer;
    static int[][] map;
    static int[] top;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            top = new int[W];
            Arrays.fill(top, H);

            for (int y = 0; y < H; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < W; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] > 0) {
                        top[x] = Math.min(top[x], y);
                    }
                }
            }

            answer = 0;
            solve(0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
    
    static int[] tgt;

    static void solve(int tgtIdx) {
        if (tgtIdx == N) {

            for (int x : tgt) {
                remove(top[x], x, map[top[x]][x]);
            }

            return;
        }
        for (int i = 0; i < W; i++) {
            tgt[tgtIdx] = i;
            solve(tgtIdx + 1);
        }
    }
    
    static int[] dy = { 0, 0, -1, 1 };
    static int[] dx = { -1, 1, 0, 0 };

    static void remove(int y, int x, int p) {
        for (int i = 0; i < 4; i++) {
            
            for (int j = 1; j < p; j++) {
                int ny = y + dy[i] * j;
                int nx = x + dx[i] * j;

                if (ny < 0 || ny >= H || nx < 0 || nx >= W)
                    continue;

                remove(ny, nx, map[ny][nx]);
            }
        }
    }
    
    static int counting() {
        int count = 0;
        for (int i = 0; i < W; i++) {
            count += (H - top[i]);
        }
        return count;
    }

}
