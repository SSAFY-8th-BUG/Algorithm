import java.io.*;
import java.util.*;

// 단방향
// 오고 가는데 가장 많은 시간을 소비하는 학생의 시간
// 도착지에서 다른 각 정점으로의 최소경로 (다익스트라)
// 인접행렬 시간초과 -> 인접리스트로 교체
// 정방향 역방향 두개의 그래프를 만들어 두번의 다익스트라로 답을 구할 수 있음 (시간있으면 해봐야게따)
public class boj_1238_tableMinPark {
    static int N, M, X;
    static List<List<Node>> graph;
    static int[] cost;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));        // 단방향

        }

        int[] go = new int[N + 1];
        for (int i = 1; i <= N; i++){
            dijkstra(i);
            go[i] = cost[X];
        }
        
        dijkstra(X);
        
        int answer = 0;
        for (int i = 1; i <= N; i++){
            int total = go[i] + cost[i];
            answer = Math.max(answer, total);
        }

        System.out.println(answer);
        br.close();
    }

    static class Node {
        int n;
        int c;
        public Node (int n, int c){
            this.n = n;
            this.c = c;
        }
    }

    static void dijkstra(int start){
        // 도착지점 0 으로 초기화
        cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);        
        q.add(new Node(start, 0));
                
        while(!q.isEmpty()){
            Node now = q.poll();

            for (Node next : graph.get(now.n)){
                int nn = next.n;
                int nw = cost[now.n] + next.c;

                if (cost[nn] > nw){
                    cost[nn] = nw;
                    q.add(new Node(nn, next.c));
                }
            }
        }
    }
}