import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    static boolean[][] vertex;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        vertex = new boolean[n + 1][n + 1];
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            vertex[a][b] = true;
            vertex[b][a] = true;
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (!vertex[a][b]) continue;
                vertex[a][b] = false;
                vertex[b][a] = false;
                int ac = bfs(a, n);
                int bc = bfs(b, n);
                answer = Math.min(answer, Math.abs(ac - bc));
                vertex[a][b] = true;
                vertex[b][a] = true;
            }
        }

        return answer;
    }

    static int bfs(int start, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[n + 1];

        q.add(start);
        v[start] = true;

        int count = 0;

        while(!q.isEmpty()) {
            int now = q.poll();

            count++;

            for (int next : graph.get(now)) {
                if (v[next]) continue;
                if (!vertex[now][next] || !vertex[next][now]) continue;
                q.add(next);
                v[next] = true;
            }
        }
        return count;
    }
}