package studygroup.Week032;
import java.util.*;
public class PG_리코쳇로봇 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] start = new int[2];
    static int[] end = new int[2];
    static char[][] map;
    static boolean[][] visited;
    static int N,M;
    static int ans = Integer.MAX_VALUE;
    class Solution {
        public int solution(String[] board) {
            map = new char[board.length][board[0].length()];
            N = map.length;
            M = map[0].length;
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    map[i][j] = board[i].charAt(j);
                    if(map[i][j] == 'R'){
                        start[0] = i;
                        start[1] = j;
                    }
                    if(map[i][j] == 'G'){
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }

            return bfs(start[0],start[1],0);
        }
        public static int bfs(int x, int y, int dis){
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{x,y,0});
            visited[x][y] = true;

            while(!q.isEmpty()){
                int[] tmp = q.poll();
                if(tmp[0] == end[0] && tmp[1] == end[1]){
                    return tmp[2];
                }
                for(int i=0; i<4; i++){
                    int[] slide = go(tmp[0],tmp[1],i);
                    if(visited[slide[0]][slide[1]]) continue;
                    visited[slide[0]][slide[1]] = true;
                    q.add(new int[] {slide[0],slide[1],tmp[2]+1});
                }
            }
            return -1;
        }
        public static int[] go(int x, int y, int d){
            int nx = x;
            int ny = y;
            while(true){
                if(nx+dx[d]<0||ny+dy[d]<0||nx+dx[d]>=N||ny+dy[d]>=M) break;
                if(map[nx+dx[d]][ny+dy[d]] == 'D') break;
                nx += dx[d];
                ny += dy[d];
            }
            return new int[]{nx,ny};
        }
    }
}
