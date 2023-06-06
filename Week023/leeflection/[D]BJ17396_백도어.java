package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17396_백도어{

    public static int N, M;
    public static boolean[] visited;
    public static long[] dist;
    public static boolean[] canGo;
    public static List<Node>[] list;

    public static class Node implements Comparable<Node>{
        int node;
        long dist;

        Node(int node, long dist){
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        dist = new long[N];
        for(int i=0; i<N; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        canGo = new boolean[N];
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(a == 0)
                canGo[i] = true;
            else
                canGo[i] = false;
        }
        canGo[N-1] = true;

        int a, b, c;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(!canGo[a] || !canGo[b]) continue;
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        dijkstra(0);

        if(dist[N-1] == Long.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(dist[N-1]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            int nowNode = node.node;
            long nowDist = node.dist;

            if(visited[nowNode])
                continue;
            visited[nowNode] = true;

            for(Node next : list[nowNode]) {
                int nextNode = next.node;
                long nextDist = next.dist;

                if(dist[nextNode] > dist[nowNode]+nextDist) {
                    dist[nextNode] = dist[nowNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }
    }
}