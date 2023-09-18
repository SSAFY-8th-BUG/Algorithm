package studygroup.Week040;
import java.util.*;
public class PG_거리두기확인하기 {
    static class Pos{
        int x,y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int xLen,yLen;
    static char[][] map;
    static ArrayList<Pos> list;
    static Queue<int[]> q;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public int[] solution(String[][] places) {
        int[] ans = new int[places.length];

        loop:
        for(int i=0; i<places.length; i++){
            //map 및 위치 정보 초기화
            xLen = places[i].length;
            yLen = places[i][0].length();
            map = new char[xLen][yLen];
            list = new ArrayList<>();
            for(int j=0; j<xLen; j++){
                for(int k=0; k<yLen; k++){
                    map[j][k] = places[i][j].charAt(k);
                    if(map[j][k] == 'P'){
                        list.add(new Pos(j,k));
                    }
                }
            }

            for(Pos p : list){
                if(!bfs(p)){
                    ans[i] = 0;
                    continue loop;
                }
            }
            ans[i] = 1;
        }
        return ans;
    }
    public static boolean bfs(Pos p){
        int x = p.x;
        int y = p.y;
        boolean[][] visited = new boolean[xLen][yLen];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y,0});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(tmp[2] == 2) continue;
            for(int i=0; i<4; i++){
                int nx = tmp[0]+dx[i];
                int ny = tmp[1]+dy[i];
                if(nx < 0||ny<0||nx>=xLen||ny>=yLen||visited[nx][ny]) continue;
                if(map[nx][ny] == 'P'){
                    return false;
                }
                if(map[nx][ny] == 'O'){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny,tmp[2]+1});
                }
            }
        }
        return true;
    }
}
