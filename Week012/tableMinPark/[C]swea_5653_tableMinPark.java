import java.io.*;
import java.util.*;

public class swea_5653_tableMinPark {

    static int T, N, M, K;
    static int[][] map;
    static boolean[][] v;
    static Queue<Node> q;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static class Node {
        int y;
        int x;
        int l;
        int t;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
        public Node(int y, int x, int l, int t){
            this.y = y;
            this.x = x;
            this.l = l;
            this.t = t;
        }
        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            if (node.y == this.y && node.x == this.x) return true;
            return false;
        }
    } 


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            q = new ArrayDeque<>();
            map = new int[401][401];
            v = new boolean[401][401];
            for (int y = 175; y < N + 175; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 175; x < M + 175; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] > 0) {
                        q.add(new Node(y, x, map[y][x], 0));
                    }
                }
            }


            for (int i = 0; i < K; i++){         
                // 퍼뜨리고
                int size = q.size();
                List<Node> addList = new ArrayList<>();     // 현재시점에 확장되는 세포좌표
                for (int j = 0; j < size; j++){
                    Node now = q.poll();

                    if (now.l == now.t){
                        for (int d = 0; d < 4; d++){
                            int ny = now.y + dy[d];
                            int nx = now.x + dx[d];

                            // 죽은놈은 못건드니까 패스
                            if (v[ny][nx]) continue;
                            
                            // 현재시점에 동시에 확장하는 세포가 있다면
                            if (map[ny][nx] != 0 && addList.contains(new Node(ny, nx))){
                                map[ny][nx] = Math.max(map[ny][nx], now.l);
                            }
                            // 빈칸이라면
                            else if (map[ny][nx] == 0){  
                                addList.add(new Node(ny, nx));
                                map[ny][nx] = now.l;
                            }
                        }
                    }

                    // 아직 살아있는 놈만 큐에 저장
                    if (now.l * 2 > now.t + 1) q.add(new Node(now.y, now.x, now.l, now.t + 1));
                    // 죽은 세포는 방문배열에 표시
                    else v[now.y][now.x] = true;
                }

                for (Node now : addList) q.add(new Node(now.y, now.x, map[now.y][now.x], 0));
            }
            sb.append("#").append(t).append(" ").append(q.size()).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}