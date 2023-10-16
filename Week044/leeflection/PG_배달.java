package studygroup.Week044;
import java.util.*;
public class PG_배달 {
    static class Edge{
        int node;
        int val;
        public Edge(int node, int val){
            this.node = node;
            this.val = val;
        }
    }
    static ArrayList<Edge>[] list;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            list[a].add(new Edge(b,c));
            list[b].add(new Edge(a,c));
        }

        int count = 0;
        int[] dist =  bfs(1,N);
        for(int i=1; i<dist.length; i++){
            if(dist[i] <= K){
                count++;
            }
        }
        return count;
    }
    public static int[] bfs(int start,int n){
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->{
            return o1.val - o2.val;
        });
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            for(Edge next : list[cur.node]){
                if(dist[next.node] > dist[cur.node] + next.val){
                    dist[next.node] = dist[cur.node] + next.val;
                    pq.add(new Edge(next.node, dist[next.node]));
                }
            }
        }
        return dist;
    }
}
