package baekjoon.백도어_17396;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    static List<Node>[] map;

    static long[] dist;

    static boolean[] visit;


    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new List[N];
        dist = new long[N];
        visit = new boolean[N];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(i != N-1 && a == 1) visit[i] = true;
            dist[i] = Long.MAX_VALUE;
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a].add(new Node(b, c));
            map[b].add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->(int)(o1.cost - o2.cost));
        dist[0] = 0;
        pq.add(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            if(n.x == N-1) break;
            if(visit[n.x]) continue;
            visit[n.x] = true;

            for (int i = 0; i < map[n.x].size(); i++) {
                Node node = map[n.x].get(i);
                if(visit[node.x]) continue;
                if(dist[node.x] > n.cost + map[n.x].get(i).cost) {
                    dist[node.x] = n.cost + map[n.x].get(i).cost;
                    pq.add(new Node(node.x, dist[node.x]));
                }
            }
        }
        System.out.println(dist[N-1] == Long.MAX_VALUE ? -1 : dist[N-1]);
    }

    static class Node{
        int x;
        long cost;
        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }
    }

}
