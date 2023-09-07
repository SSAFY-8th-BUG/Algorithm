import java.util.*;

class PG_리코쳇로봇 {
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int N, M, sy, sx, ey, ex, answer;
    static char[][] map;
    static class P {
        int y;
        int x;
        int d;
        public P(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();

        map = new char[N][M];
        for (int y = 0; y < N; y++) {
            map[y] = board[y].toCharArray();

            for (int x = 0; x < M; x++) {
                if (map[y][x] == 'R') {
                    sy = y;
                    sx = x;
                    map[y][x] = '.';
                } else if(map[y][x] == 'G') {
                    ey = y;
                    ex = x;
                    map[y][x] = '.';
                }
            }
        }

        answer = Integer.MAX_VALUE;
        bfs(sy, sx);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    static void bfs(int y, int x) {
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        q.add(new P(y, x, 0));
        v[y][x] = true;

        while(!q.isEmpty()) {
            P now = q.poll();

            if (now.d >= answer) {
                continue;
            }

            if (now.y == ey && now.x == ex) {
                answer = Math.min(answer, now.d);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y;
                int nx = now.x;

                while(true) {
                    int ty = ny + dy[i];
                    int tx = nx + dx[i];

                    if (!isValid(ty, tx)) {
                        break;
                    }

                    ny = ty;
                    nx = tx;
                }

                if (v[ny][nx]) continue;

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }
    }

    static boolean isValid(int y, int x) {
        if (y < 0 || y >= N || x < 0 || x >= M) {
            return false;
        } else if (map[y][x] == 'D') {
            return false;
        }
        return true;
    }
}