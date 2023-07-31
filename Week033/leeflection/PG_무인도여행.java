package studygroup.Week032;
import java.util.*;
import java.io.*;
public class PG_무인도여행 {
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] map;
    static int R;
    static int C;

    public ArrayList solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        map = new int[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                char tmp = maps[i].charAt(j);
                if(!Character.isDigit(tmp)){
                    map[i][j] = -1;
                }
                else{
                    map[i][j] = tmp - '0';
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!visited[i][j] && map[i][j] > 0){
                    list.add(bfs(i,j));
                }
            }
        }
        if(list.size()==0){
            ArrayList<Integer> list2 = new ArrayList<>();
            list2.add(-1);
            return list2;
        }
        else{
            Collections.sort(list);
            return list;
        }

    }
    public int bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r,c});
        visited[r][c] = true;
        int res = map[r][c];

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            for(int i=0; i<4; i++){
                int nx = tmp[0]+dx[i];
                int ny = tmp[1]+dy[i];

                if(nx<0||ny<0||nx>=R||ny>=C || map[nx][ny] < 0 || visited[nx][ny]) continue;
                res += map[nx][ny];
                visited[nx][ny] = true;
                q.add(new int[] {nx,ny});
            }
        }

        return res;
    }
}
