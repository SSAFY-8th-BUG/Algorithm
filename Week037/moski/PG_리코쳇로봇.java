package Week037.moski;

import java.util.*;
public class PG_리코쳇로봇 {
    static char[][] map;

    static int M,N;
    static int ry,rx;
    static int gy,gx;
    static int answer;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(String[] board) {
        answer = -1;
        M = board.length;
        N = board[0].length();
        map = new char[M][N];

        for(int i=0;i<M;i++){
            map[i] = board[i].toCharArray();
            for(int j=0;j<N;j++){
                if(map[i][j] == 'R'){
                    ry = i;
                    rx = j;
                }else if(map[i][j] == 'G'){
                    gy = i;
                    gx = j;
                }
            }
        }

        boolean ok = false;
        for(int i=0;i<4;i++){
            int ny = gy + dy[i];
            int nx = gx + dx[i];

            if(ny<0 || nx<0 || ny>=M || nx>=N){
                ok = true;
                break;
            }
            if(map[ny][nx] != '.'){
                ok = true;
                break;
            }
        }

        if(ok){
            bfs();
        }



        return answer;
    }

    static void bfs(){

        boolean[][] visited = new boolean[M][N];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        pq.add(new Node(ry, rx, 0));
        visited[ry][rx] = true;

        while(!pq.isEmpty()){
            Node n = pq.poll();
            if(n.y == gy && n.x == gx){
                answer = n.cnt;
                return;
            }

            for(int i=0;i<4;i++){
                int ny = n.y + dy[i];
                int nx = n.x + dx[i];

                if(ny<0 || nx<0 || ny>=M || nx>=N || map[ny][nx] == 'D'){
                    continue;
                }

                while(true){
                    if(ny<0 || nx<0 || ny>=M || nx>=N || map[ny][nx] == 'D') {
                        ny -= dy[i];
                        nx -= dx[i];
                        break;
                    }
                    ny += dy[i];
                    nx += dx[i];
                }
                if(visited[ny][nx]) continue;
                visited[ny][nx] = true;
                pq.add(new Node(ny, nx, n.cnt+1));


            }
        }

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
