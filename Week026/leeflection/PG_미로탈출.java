package studygroup.week26;
import java.util.*;

public class PG_미로탈출 {
    static int[] start = new int[2];
    static int[] lever = new int[2];
    static int first;
    static int second;
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public int solution(String[] maps) {
        int answer = 0;
        map = new char[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                map[i][j] = maps[i].charAt(j);
                if(maps[i].charAt(j)=='S'){
                    start[0] = i;
                    start[1] = j;
                }else if(maps[i].charAt(j)=='L'){
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
        visited = new boolean[maps.length][maps[0].length()];
        first = bfs(start[0],start[1],'L');
        visited = new boolean[maps.length][maps[0].length()];
        second = bfs(lever[0],lever[1],'E');
        if(first==-1||second==-1) return -1;
        return first+second;
    }
    static int bfs(int x, int y, char c){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y,0});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(map[tmp[0]][tmp[1]]==c){
                return tmp[2];
            }
            for(int k=0; k<4; k++){
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];

                if(nx<0||ny<0||nx>=map.length||ny>=map[0].length||visited[nx][ny]||map[nx][ny]=='X') continue;
                q.add(new int[]{nx,ny,tmp[2]+1});
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}
