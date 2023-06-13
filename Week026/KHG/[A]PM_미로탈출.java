import java.util.*;
class Solution {
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    static char[][] mat;
    static int H,W,sx,sy,answer;
    public int solution(String[] maps) {
        answer = 0;
        H = maps.length;
        W = maps[0].length();
        mat = new char[H][W];
        init(maps);
        //System.out.println(sx + " " +sy);
        int L = go('L');
        if(L == -1) return -1;
        //System.out.println(sx + " " +sy);
        int E = go('E');
        if(E == -1) return -1;
        answer = L + E;
        
        return answer;
    }
    
    static void init(String[] maps){
        for(int y=0; y<H; y++){
            mat[y] = maps[y].toCharArray();
            for(int x=0; x<W; x++){
                if(mat[y][x] == 'S'){
                    sx=x; sy=y;
                }
            }
        }
    }
    static int go(char C){
        boolean[][] visited = new boolean[H][W];
        visited[sy][sx]=true;
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(sx,sy,0));
        while(!que.isEmpty()){
            Point cur = que.pollFirst();
            int cx = cur.x, cy=cur.y;
            for(int dr=0; dr<4; dr++){
                int nx = cx + dirs[dr][0];
                int ny = cy + dirs[dr][1];
                if(ny>=H || ny<0 || nx>=W || nx<0)continue;
                if(mat[ny][nx] == 'X' || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                if(mat[ny][nx] == C) {
                    sx=nx; sy=ny;
                    return cur.w+1;
                }
                que.add(new Point(nx,ny,cur.w+1));
            }
        }
        
        return -1;
    }
    
    static class Point{
        int x,y,w;
        Point(int x, int y, int w){
            this.x=x; this.y=y; this.w=w;
        }
    }
}