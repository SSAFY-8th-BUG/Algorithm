import java.util.*;

class Solution {

    static List<List<Integer>> graph;
    static int[] dist;
    static int max;
    static class Node {
        int n;
        int c;
        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        max = 0;
        dijkstra(n, 1);

        for (int i = 1; i <= n; i++) {
            if (dist[i] == max)
                answer++;
        }

        return answer;
    }

    static void dijkstra(int n, int start) {
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);
        boolean[] v = new boolean[n + 1];

        q.add(new Node(start, 0));

        while(!q.isEmpty()) {
            Node now = q.poll();

            if (v[now.n]) continue;
            v[now.n] = true;

            for (int next : graph.get(now.n)) {
                if (dist[next] > dist[now.n] + 1) {
                    dist[next] = dist[now.n] + 1;
                    max = Math.max(max, dist[next]);
                    q.add(new Node(next, dist[next]));
                }
            }
        }
    }
}