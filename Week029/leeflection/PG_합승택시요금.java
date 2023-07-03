package studygroup.Week029;
import java.util.*;
public class PG_합승택시요금 {
    /**
     어피치 , 무지
     비슷한 방향으로 가는 택시를 이용
     택시 합승을 적절히 이용하면 택시요금을 얼마나 아낄수 있을까
     **/
        static class Edge{
            int node,val;
            public Edge(int node, int val){
                this.node = node;
                this.val =val;
            }
        }
        static ArrayList<Edge>[] list;
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            list = new ArrayList[n+1];

            for(int i=0; i<=n; i++){
                list[i] = new ArrayList<Edge>();
            }

            for(int i=0; i<fares.length; i++){
                int q = fares[i][0];
                int w = fares[i][1];
                int e = fares[i][2];

                list[q].add(new Edge(w,e));
                list[w].add(new Edge(q,e));
            }

            int dist[] = dijkstra(n,s);
            int adist[] = dijkstra(n,a);
            int bdist[] = dijkstra(n,b);
            //따로 갔을때
            int min = dist[a] + dist[b];
            //int min = Integer.MAX_VALUE;
            for(int i=1; i<=n; i++){
                if(min > dist[i] + adist[i] + bdist[i]){
                    min = dist[i] + adist[i] + bdist[i];
                }
            }
            return min;
        }
        static int[] dijkstra(int n,int s){
            int[] dist = new int[n+1];
            Arrays.fill(dist,Integer.MAX_VALUE);
            dist[s] = 0;

            boolean[] visited = new boolean[n+1];

            PriorityQueue<Edge> pq = new PriorityQueue<>((p1,p2)->{
                return p1.val - p2.val;
            });

            pq.add(new Edge(s,0));

            while(!pq.isEmpty()){
                Edge cur = pq.poll();

                visited[cur.node] = true;

                for(Edge e : list[cur.node]){
                    if(!visited[e.node] && dist[e.node] > dist[cur.node] + e.val){
                        dist[e.node] = dist[cur.node] + e.val;
                        pq.add(new Edge(e.node,dist[cur.node] + e.val));
                    }
                }
            }
            return dist;
        }
}
