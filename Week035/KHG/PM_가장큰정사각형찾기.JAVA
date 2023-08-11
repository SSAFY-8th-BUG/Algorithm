import java.util.*;

class Solution
{

    public int solution(int [][]board)
    {
        int answer = 0;
        int H = board.length;
        int W = board[0].length;
        if(H==1 || W==1){
            for(int y=0; y<H; y++){
                for(int x=0; x<W; x++){
                    if(board[y][x]==1)return 1;
                }
            }
            return 0;
        }
        
        int[][]mat = board;
        for(int y=1; y<H; y++){
            for(int x=1; x<W; x++){
                if(mat[y][x]==1){
                    int lu = mat[y-1][x-1];
                    int l = mat[y][x-1];
                    int u = mat[y-1][x];
                    if(lu==0 && l==0 && u==0) continue;
                    int min = Math.min(lu,l);
                    min = Math.min(min,u);
                    mat[y][x]=min+1;
                    answer=Math.max(answer, min+1);
                }
            }
        }
        
        

        return answer*answer;
    }
    
    
}