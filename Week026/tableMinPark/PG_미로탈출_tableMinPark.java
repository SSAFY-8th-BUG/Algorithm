import java.util.*;

class PG_미로탈출_tableMinPark {

    static char[][] map;
    static int h, w;
    static P s, l, e;

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

    public int solution(String[] maps) {
        int answer = 0;

        h = maps.length;
        w = maps[0].length();

        map = new char[h][w];
        for (int y = 0; y < h; y++){
            map[y] = maps[y].toCharArray();
            for (int x = 0; x < w; x++){
                if (map[y][x] == 'S') {
                    s = new P(y, x, 0);
                } else if (map[y][x] == 'L') {
                    l = new P(y, x, 0);
                } else if (map[y][x] == 'E') {
                    e = new P(y, x, 0);
                }
            }
        }

        int d1 = bfs(s, l);
        int d2 = bfs(l, e);

        if (d1 != Integer.MAX_VALUE && d2 != Integer.MAX_VALUE) {
            answer = d1 + d2;
        } else {
            answer = -1;
        }
        return answer;
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    int bfs(P start, P end) {
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[h][w];

        q.add(start);
        v[start.y][start.x] = true;

        int dis = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            P now = q.poll();

            if (now.y == end.y && now.x == end.x){
                dis = Math.min(dis, now.d);
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w || v[ny][nx] || map[ny][nx] == 'X') continue;

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }
        return dis;
    }
}