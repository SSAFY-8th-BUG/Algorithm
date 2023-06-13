package Week026.moski;
import java.util.*;

public class PG_미로탈출_moski {
    static char[][] map;
    static int sx, sy, lx, ly, ex, ey, ans, sizeX,sizeY;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(String[] maps) {
        sizeY = maps.length;
        sizeX = maps[0].length();

        sx = 0;
        sy = 0;
        lx = 0;
        ly = 0;
        ex = 0;
        ey = 0;
        ans = 0;

        map = new char[sizeY][sizeX];

        for(int i=0;i<sizeY;i++){
            map[i] = maps[i].toCharArray();
            for(int j=0;j<sizeX;j++){
                if(map[i][j] == 'S'){
                    sx = j;
                    sy = i;
                }
                if(map[i][j] == 'L'){
                    lx = j;
                    ly = i;
                }
                if(map[i][j] == 'E'){
                    ex = j;
                    ey = i;
                }
            }
        }

        bfs(new Node(sy, sx, 0), new Node(ly, lx, 0));
        if(ans == -1){
            return ans;
        }

        bfs(new Node(ly, lx, 0), new Node(ey, ex, 0));
        if(ans == -1){
            return ans;
        }

        return ans;
    }

    static void bfs(Node start, Node end){
        boolean[][] visit = new boolean[sizeY][sizeX];
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cnt - n2.cnt);

        visit[start.y][start.x] = true;
        pq.add(start);

        while(!pq.isEmpty()){
            Node node = pq.poll();


            if(node.y == end.y && node.x == end.x){
                ans += node.cnt;
                return;
            }

            for(int i=0; i<4; i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(ny<0 || nx<0 || ny>=sizeY || nx>=sizeX) continue;
                if(visit[ny][nx]) continue;
                if(map[ny][nx] != 'X'){
                    visit[ny][nx] = true;
                    pq.add(new Node(ny, nx, node.cnt+1));
                }
            }
        }
        ans = -1;
    }

    static class Node{
        int y,x;
        int cnt;

        public Node(int y, int x, int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
