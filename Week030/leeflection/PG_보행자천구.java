package studygroup.Week030;

public class PG_보행자천구 {
    int MOD = 20170805;
    static int N,M;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] map;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[m+1][n+1][2];
        dp[1][1][1] = dp[1][1][0] = 1;
        for(int r=1; r<=m; r++){
            for(int c=1; c<=n; c++){
                if(cityMap[r - 1][c - 1] == 0){
                    dp[r][c][0] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                    dp[r][c][1] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                } else if(cityMap[r - 1][c - 1] == 1){
                    dp[r][c][0] = 0;
                    dp[r][c][1] = 0;
                } else {
                    dp[r][c][0] = dp[r - 1][c][0];
                    dp[r][c][1] = dp[r][c - 1][1];
                }
            }
        }
        return dp[m][n][0];
    }
}
