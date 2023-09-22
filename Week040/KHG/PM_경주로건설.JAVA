import java.util.*;

class Solution {
    static int H,W;
    static int[][][] visited;
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    static int[][] board;
    public int solution(int[][] board2) {
        board = board2;
        int answer;
        H = board.length;
        W = board[0].length;
        visited = new int[2][H][W];
        for(int i=0; i<2; i++){
            for(int y=0; y<H; y++){
                for(int x=0; x<W; x++){
                    visited[i][y][x]=Integer.MAX_VALUE;
                }
            }
        }
        bfs();
        answer = Math.min(visited[0][H-1][W-1],visited[1][H-1][W-1]);
        
        return answer;
    }
    
    void bfs(){
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(0,0,0,0)); //우 시작
        que.add(new Point(0,0,0,1)); //하 시작
        visited[0][0][0]=0;
        visited[1][0][0]=0;
        while(!que.isEmpty()){
            Point cur = que.pollFirst();
            int cx=cur.x;
            int cy=cur.y;
            int cdr=cur.dr;
            int cw=cur.w;
            
            //System.out.println(cx+" "+cy+" "+cdr+" "+cw);
            for(int dr=0; dr<4; dr++){
                
                int nx = cx+dirs[dr][0];
                int ny = cy+dirs[dr][1];
                int nw = cw+100; //직진
                if(dr%2 !=cdr%2)nw+=500; //코너
                if(nx<0 || ny<0 || nx>=W || ny>=H || board[ny][nx]==1) continue;
                if(visited[dr%2][ny][nx]<=nw) continue;
                
                visited[dr%2][ny][nx]=nw;
                que.add(new Point(nx,ny,nw,dr));
                
            }
        }
    }
    
    
    class Point{
        int x,y,w,dr;
        Point(int x, int y, int w, int dr){
            this.x=x;
            this.y=y;
            this.w=w;
            this.dr=dr;
        }
    }
}