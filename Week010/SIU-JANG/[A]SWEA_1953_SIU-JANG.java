package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_탈주범검거_1953 {
     
    static int T, N, M, R, C, L, ans;
    static int[][] map;
     
    // bfs
    static boolean[][] checked;
    static Queue<Node> q = new ArrayDeque<>();
     
    // delta
    // 상, 하, 좌, 우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
     
    public static void main(String[] args) throws Exception {
         
        // 입력 <
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
         
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
             
            ans = 0;
             
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // >
             
            q.clear();
            q.add(new Node(R, C));
             
            checked = new boolean[N][M];
            checked[R][C] = true;
             
            // bfs를 사용해서 답을 찾는다.
            bfs();
             
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (checked[i][j]) ans++;
                }
            }
             
            System.out.println("#" + test_case + " " + ans);
        }
    }
     
    static void bfs() {
        int ret = 1;
         
        while (!q.isEmpty()) {
            if (ret == L) {
                q.clear();
                break;
            }
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                 
                int x = n.x;
                int y = n.y;
                 
                // 1번 파이프
                if (map[x][y] == 1) {
                    for (int d = 0; d < 4; d++) {                    
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));                                
                            } else if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }               
                // 2번 파이프
                else if (map[x][y] == 2) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
                 
                // 3번 파이프
                else if (map[x][y] == 3) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
                 
                // 4번 파이프
                else if (map[x][y] == 4) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
                 
                // 5번 파이프
                else if (map[x][y] == 5) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
                 
                // 6번 파이프
                else if (map[x][y] == 6) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
                 
                else if (map[x][y] == 7) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                         
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
                            if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            } else if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
                                checked[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                        }
                    }
                }
            }
             
            ret++;
        }
    }
     
    static class Node {
        int x;
        int y;
         
        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}