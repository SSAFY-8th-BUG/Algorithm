package baekjoon.미로만들기_2665;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static int[][] map;
    static int[][] dist;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dist = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = c[j]-'0';
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> o1.cost == o2.cost ? o1.y-o2.y : o1.cost-o2.cost);

        pq.add(new Node(0,0,0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.y][node.x]) continue;
            visit[node.y][node.x] = true;
            dist[node.y][node.x] = node.cost;
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
                if(visit[ny][nx]) continue;
                if(map[ny][nx] == 0) {
                    pq.add(new Node(ny, nx, node.cost+1));
                }else {
                    pq.add(new Node(ny, nx, node.cost));
                }
            }
        }

        System.out.println(dist[N-1][N-1]);
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
