package baekjoon.알고스팟_1261;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        dist = new int[M+1][N+1];
        visit = new boolean[M+1][N+1];

        for (int i = 1; i <= M; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 1; j <= N; j++) {
                map[i][j] = c[j-1] - '0';
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> o1.cost == o2.cost ? o1.y-o2.y : o1.cost-o2.cost);

        pq.add(new Node(1,1,0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.y][node.x]) continue;
            visit[node.y][node.x] = true;
            dist[node.y][node.x] = node.cost;
            if(node.y == N && node.x == M) break;
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny<1 || nx<1 || ny>M || nx>N) continue;
                if(visit[ny][nx]) continue;
                if(map[ny][nx] == 1) {
                    pq.add(new Node(ny, nx, node.cost+1));
                }else {
                    pq.add(new Node(ny, nx, node.cost));
                }
            }
        }

        System.out.println(dist[M][N]);
    }

    static class Node {
        int y,x,cost;
        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}
