package studygroup.Week040;
import java.util.*;
public class PG_경주로건설 {
    static int[][] map;
    static int[][][] dist;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;

    public int solution(int[][] board) {
        map = board;
        N = map.length;
        M = map[0].length;
        dist = new int[map.length][map[0].length][4];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                for (int k = 0; k < 4; k++) {
                    Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                }
            }
        }
        dijkstra();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, dist[N - 1][M - 1][i]);
        }
        return ans;
    }

    public static void dijkstra() {
        for (int i = 0; i < 4; i++) {
            dist[0][0][i] = 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[3] - o2[3];
        });
        pq.add(new int[]{0, 0, -1, 0});
        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                int cost = tmp[3];
                int pos = tmp[2];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 1) continue;
                //상우하좌
                cost += 100;

                if (pos == 0 || pos == 2) {
                    if (i == 1 || i == 3) {
                        cost += 500;
                    }
                }
                if (pos == 1 || pos == 3) {
                    if (i == 0 || i == 2) {
                        cost += 500;
                    }
                }

                if (cost < dist[nx][ny][i]) {
                    dist[nx][ny][i] = cost;
                    pq.add(new int[]{nx, ny, i, cost});
                }

            }
        }
    }
}
