import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_코드트리빵 {
    static final int MAX_S = 255;

    static int n, m;
    static int time;
    static Node[] humans;

    static boolean[][] visited;
    static int[][] map;
    static int[][] step;

    static List<Node> baseCamp = new ArrayList<>();
    static Node[] store = new Node[MAX_S+1];

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = 0;

        map = new int[n+1][n+1];
        step = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];

        humans = new Node[m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) baseCamp.add(new Node(i, j));
            }
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            store[i] = new Node(y, x);
        }

        while (true){
            time++;

            // 1. 현재 격자판에서 이동중인 사람 이동
            for(int i=1;i<time;i++){
                if(i > m) break;
                // 편의점 도착했다면
                if(humans[i].y == -1 && humans[i].x == -1){
                    continue;
                }

                bfs(store[i]);

                int hy = humans[i].y;
                int hx = humans[i].x;

                int minD = Integer.MAX_VALUE;
                int minY = -1;
                int minX = -1;
                for(int d=0;d<4;d++){
                    int ny = hy + dy[d];
                    int nx = hx + dx[d];

                    if(ny<=0 || nx<=0 || ny>n || nx>n) continue;
                    if(!visited[ny][nx]) continue;
                    if(minD > step[ny][nx]){
                        minD = step[ny][nx];
                        minY = ny;
                        minX = nx;
                    }
                }

                humans[i] = new Node(minY, minX);
            }

            // 2. 편의점 도착시 락
            for(int i=1;i<time;i++){
                if(i > m) break;
                if(humans[i].equals(store[i])){
                    humans[i].y = -1;
                    humans[i].x = -1;
                    map[store[i].y][store[i].x] = 2;
                }
            }

            // 3. 새로운 인원 넣기

            if(time <= m) {

                // 새로운 편의점
                bfs(store[time]);
                // 사람 입장
                int minD = Integer.MAX_VALUE;
                int minY = -1;
                int minX = -1;

                for(int i=1;i<=n;i++){
                    for(int j=1;j<=n;j++){
                        // 방문 가능한 베이스 캠프 중 거리가 가장 가까운 베이스 캠프
                        if(visited[i][j] && map[i][j] == 1 && minD > step[i][j]){
                            minD = step[i][j];
                            minY = i;
                            minX = j;
                        }
                    }
                }

                // 우선 순위가 높은 베이스캠프로 이동
                humans[time] = new Node(minY, minX);
                map[minY][minX] = 2;
            }

            // 4. 끝난지 판단
            if(time >= m){
                boolean ok = true;
                for(int i=1;i<=m;i++){
                    if(humans[i].y != -1 || humans[i].x != -1){
                        ok = false;
                        break;
                    }
                }
                if(ok) break;
            }

        }
        System.out.println(time);
    }

    static void bfs(Node store){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }
        visited[store.y][store.x] = true;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(store.y, store.x));

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0;i<4;i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny<=0 || nx<=0 || ny>n || nx>n) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx] == 2) continue;

                visited[ny][nx] = true;
                step[ny][nx] = step[node.y][node.x] + 1;
                q.add(new Node(ny, nx));

            }
        }


    }

    static boolean cmpStore(Node a, Node b){
        if(a.y != b.y) return a.y > b.y;
        else return a.x > b.x;
    }
    static class Node{
        int y,x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Node){
                Node node = (Node) o;
                if(this.y == node.y && this.x == node.x) return true;
            }
            return false;
        }
    }
}
