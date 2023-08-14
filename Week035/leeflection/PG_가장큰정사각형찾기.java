package studygroup.Week032;

public class PG_가장큰정사각형찾기 {
    public int solution(int [][]board)
    {
        int[][] dp = new int[board.length+1][board[0].length+1];
        int max = Integer.MIN_VALUE;
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(board[i-1][j-1] != 0){
                    int min = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                    dp[i][j] = min+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
