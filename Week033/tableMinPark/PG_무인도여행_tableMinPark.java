import java.util.*;

class Solution {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int[][] map;
    static boolean[][] v;
    static int N, M;

    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        v = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            char[] temp = maps[y].toCharArray();

            for (int x = 0; x < M; x++) {
                if (temp[x] == 'X') {
                    v[y][x] = true;
                } else {
                    map[y][x] = temp[x]-'0';
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (v[y][x]) continue;
                int days = bfs(y, x);
                answer.add(days);
            }
        }

        Collections.sort(answer);

        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }

        if (answer.size() == 0)
            return new int[]{-1};
        else
            return ans;
    }

    static class Node {
        int y;
        int x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        int days = 0;

        q.add(new Node(y, x));
        v[y][x] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();

            days += map[now.y][now.x];

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx]) continue;

                q.add(new Node(ny, nx));
                v[ny][nx] = true;
            }
        }

        return days;
    }
}