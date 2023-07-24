import java.util.*;
class Solution {
    static int[][] mat;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        mat = new int[rows][columns];
        int num=1;
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                mat[y][x] = num++;
            }
        }
        for(int i=0; i<queries.length; i++){
            int[] query = queries[i];
            int sx = query[1]-1;
            int sy = query[0]-1;
            int ex = query[3]-1;
            int ey = query[2]-1;
            //System.out.println(sx+" "+sy+" "+ex+" "+ey);
            int min=move(sx,sy,ex,ey);
            answer[i]=min;
        }
        return answer;
    }
    
    static int move(int sx, int sy, int ex, int ey){
        int w = ex-sx+1;
        int h = ey-sy+1;
        int temp = mat[sy][ex];
        int min=temp;
        for(int i=0; i<w-1; i++){
            mat[sy][ex-i] = mat[sy][ex-i-1];
            min = Math.min(min, mat[sy][ex-i]);
        }
        for(int i=0; i<h-1; i++){
            mat[sy+i][sx] = mat[sy+i+1][sx];
            min = Math.min(min, mat[sy+i][sx]);
        }
        for(int i=0; i<w-1; i++){
            mat[ey][sx+i] = mat[ey][sx+i+1];
            min = Math.min(min, mat[ey][sx+i]);
        }
        for(int i=0; i<h-1; i++){
            mat[ey-i][ex] = mat[ey-i-1][ex];
            min = Math.min(min, mat[ey-i][ex]);
        }
        mat[sy+1][ex] = temp;
        
        return min;
    }
}