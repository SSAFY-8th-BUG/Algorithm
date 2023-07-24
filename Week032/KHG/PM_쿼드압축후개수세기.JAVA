import java.util.*;

class Solution {
    static int[][] mat;
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[]{0,0};
        mat = arr;
        rec(0,0,arr.length);
        return answer;
    }
    
    static void rec(int sx, int sy, int w){
        int num0=0, num1=0;
        for(int x=sx; x<sx+w; x++){
            for(int y=sy; y<sy+w; y++){
                if (mat[y][x]==0) num0++;
                else num1++;
            }
        }
        if(num0==0){
            answer[1]+=1;
            return;
        }else if(num1==0){
            answer[0]+=1;
            return;
        }else{
            int w2 = w/2;
            rec(sx,sy,w2);
            rec(sx+w2,sy,w2);
            rec(sx,sy+w2,w2);
            rec(sx+w2,sy+w2,w2);
        }
        
    }
}