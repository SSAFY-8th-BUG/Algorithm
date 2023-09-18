import java.util.*;

class Solution {
    static char[][] mat;
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for(int i=0; i<places.length; i++){
            int result = check(places[i]);
            answer[i]=result;
        }
        return answer;
    }
    
    int check(String[] place){
        mat = new char[5][];
        for(int i=0; i<5; i++){
            mat[i] = place[i].toCharArray();
        }
        
        for(int y=0; y<5; y++){
            for(int x=0; x<5; x++){
                if(mat[y][x]=='P'){ //P마다 bfs해봄
                    if(!bfs(x,y)) return 0;
                }
            }
        }
        return 1;
    }
    
    boolean bfs(int sx, int sy){
        boolean[][] visited = new boolean[5][5];
        visited[sy][sx]=true;
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(sx,sy,0));
        while(!que.isEmpty()){
            Point cur = que.pollFirst();
            if(cur.w>=2) break;
            int cx = cur.x;
            int cy = cur.y;
            for(int dr=0; dr<4; dr++){
                int nx = cx+dirs[dr][0];
                int ny = cy+dirs[dr][1];
                if(nx<0 || ny<0 || nx>=5 || ny>=5 || visited[ny][nx] || mat[ny][nx]=='X') continue;
                if(mat[ny][nx]=='P') return false;
                visited[ny][nx]=true;
                que.add(new Point(nx,ny,cur.w+1));
            }
        }
        
        return true;
    }
    
    class Point{
        int x, y,w;
        Point(int x, int y, int w){
            this.x=x;
            this.y=y;
            this.w=w;
        }
    }
}