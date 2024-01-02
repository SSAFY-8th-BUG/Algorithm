import java.util.*;

class Solution {
    static int MOD = 20170805;
    static int H,W;
    static int[][] cityMap;
    static int[][][] dp;
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        H=m;
        W=n;
        this.cityMap = cityMap;
        dp = new int[H][W][2]; //좌 상
        
        dp[0][0][0]=1;
        
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                if(y==0 && x==0 )continue; 
                makeDp(x,y);
            }
        }
        
        /*for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                System.out.print(Arrays.toString(dp[y][x]));
            }System.out.println();
        }*/
        return (dp[H-1][W-1][0]+dp[H-1][W-1][1])%MOD;
        
    }
    
    void makeDp(int cx, int cy){
        for(int dr=0; dr<2; dr++){
            int rdr = (dr+2)%4;
            int px = cx+dirs[rdr][0];
            int py = cy+dirs[rdr][1];
            if(px<0 || py<0)continue;
            if(cityMap[py][px]==1) continue;
            int prev=dp[py][px][dr];
            if(cityMap[py][px]==0) prev+=dp[py][px][(dr+1)%2];
            dp[cy][cx][dr]=(prev%MOD);
        }
        
    }
}
    
    