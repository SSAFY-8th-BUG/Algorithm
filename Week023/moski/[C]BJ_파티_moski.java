package baekjoon.파티_1238;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X;
    static int maxDist;
    static final int INF = 99999999;

    static int[] dist;
    static int[] dist2;
    static int[][] map;

    static boolean[] visit;

    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->(o1.w - o2.w));

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        maxDist = 0;

        dist = new int[N+1];
        dist2 = new int[N+1];
        map = new int[N+1][N+1];



        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
            dist2[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
        }

        visit = new boolean[N+1];
        d1();
        pq.clear();
        visit = new boolean[N+1];
        d2();

        for (int i = 1; i <= N; i++) {
            if(maxDist < dist[i]+dist2[i]) {
                maxDist = dist[i] + dist2[i];
            }
        }
        System.out.println(maxDist);


    }

    static void d1() {
        dist[X] = 0;
        for (int i = 1; i <= N; i++) {
            if(map[X][i]!=0) {
                dist[i] = map[X][i];
                pq.offer(new Node(i,map[X][i]));
            }
        }
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int n = node.n;
            if(visit[n]) continue;
            visit[n] = true;

            for (int i = 1; i <= N; i++) {
                if(visit[i]) continue;
                if(map[n][i]!=0) {
                    if(dist[i] > dist[n] + map[n][i]) {
                        dist[i] = dist[n] + map[n][i];
                        pq.offer(new Node(i, dist[i]));
                    }
                }
            }
        }

    }

    static void d2() {
        dist2[X] = 0;
        for (int i = 1; i <= N; i++) {
            if(map[i][X]!=0) {
                dist2[i] = map[i][X];
                pq.offer(new Node(i,map[i][X]));
            }
        }
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int n = node.n;
            if(visit[n]) continue;
            visit[n] = true;

            for (int i = 1; i <= N; i++) {
                if(visit[i]) continue;
                if(map[i][n]!=0) {
                    if(dist2[i] > dist2[n] + map[i][n]) {
                        dist2[i] = dist2[n] + map[i][n];
                        pq.offer(new Node(i, dist2[i]));
                    }
                }
            }
        }

    }

    static class Node{
        int n, w;

        Node(int n, int w){
            this.n = n;
            this.w = w;
        }
    }

}