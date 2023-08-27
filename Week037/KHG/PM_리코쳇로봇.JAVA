import java.util.*;

class Solution {
    static char[][] mat;
    static int H, W, sx, sy, ey, ex;
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    static boolean[][] visited;
    public int solution(String[] board) {
        H = board.length;
        W = board[0].length();
        System.out.println(W+" "+H);
        mat = new char[H][W];
        visited = new boolean[H][W];
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                mat[y][x] = board[y].charAt(x);
                if(mat[y][x] =='R'){
                    sx=x; sy=y;
                }else if(mat[y][x] == 'G'){
                    ex=x; ey=y;
                }
            }
        }
        
        int answer = -1;
        if(!check())return -1;
        answer = find();
        return answer;
    }
    
    static boolean check(){ //갈수있는 위치인지
        if(ex==0 || ex ==W-1 || ey==0 || ey==H-1)return true;
        for(int dr=0; dr<4; dr++){
            int nx = ex+dirs[dr][0];
            int ny = ey+dirs[dr][1];
            if(mat[ny][nx]=='D')return true;
        }
        return false;
    }
    
    static int find(){ //bfs
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(sx,sy,0));
        visited[sy][sx]=true;
        while(!que.isEmpty()){
            Point cur = que.pollFirst();
            for(int dr=0; dr<4; dr++){
                int cx=cur.x;
                int cy=cur.y;
                int nx= cx+dirs[dr][0];
                int ny= cy+dirs[dr][1];
                
                while(nx>=0 && nx<W && ny>=0 && ny<H && mat[ny][nx]!='D'){
                    cx=nx; cy=ny;
                    nx=cx+dirs[dr][0];
                    ny=cy+dirs[dr][1];
                }
                
                if(mat[cy][cx]=='G')return cur.w+1;
                if(visited[cy][cx])continue;
                visited[cy][cx]=true;
                que.addLast(new Point(cx,cy,cur.w+1));
                
            }
        }
        return -1;
        
    }
    
    static class Point{
        int x,y,w;
        Point(int x, int y, int w){
            this.x=x;
            this.y=y;
            this.w=w;
        }
    }
}