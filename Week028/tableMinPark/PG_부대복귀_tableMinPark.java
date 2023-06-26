import java.util.*;

// DFS > 시간초과
class PG_부대복귀_tableMinPark_DFS {

    static List<List<Integer>> graph;
    static boolean[] v;
    static int ans;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads){
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        for (int i = 0; i < sources.length; i++){
            ans = Integer.MAX_VALUE;
            v = new boolean[n + 1];
            v[sources[i]] = true;
            dfs(sources[i], 0, destination);

            answer[i] = ans == Integer.MAX_VALUE ? -1 : ans;
        }



        return answer;
    }

    void dfs(int n, int d, int dest){
        if (d >= ans){
            return;
        }
        if (n == dest){
            ans = Math.min(ans, d);
            return;
        }

        for (int next : graph.get(n)){
            if (v[next]) continue;
            v[next] = true;
            dfs(next, d + 1, dest);
            v[next] = false;
        }
    }
}

import java.util.*;
class PG_부대복귀_tableMinPark_다익스트라 {

    static List<List<Integer>> graph;
    static boolean[] v;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;
    static class Node {
        int n;
        int c;
        public Node(int n, int c){
            this.n = n;
            this.c = c;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads){
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        dijkstra(n, destination, sources);

        for (int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]] == INF ? -1 : dist[sources[i]];
        }

        return answer;
    }

    void dijkstra(int n, int start, int[] sources) {
        dist = new int[n + 1];
        v = new boolean[n + 1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>((Node n1, Node n2) -> n1.c - n2.c);
        q.offer(new Node(start, 0));

        while(!q.isEmpty()) {
            int now = q.poll().n;

            if(v[now]) continue;
            v[now] = true;

            for (int next : graph.get(now)){
                if (dist[next] > dist[now] + 1){
                    dist[next] = dist[now] + 1;
                    q.offer(new Node(next, dist[next]));
                }
            }
        }

    }
}