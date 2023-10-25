public class PG_올바른괄호의갯수 {
    static int[] dp;
    public int solution(int n) {

        dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i] += dp[i-j] * dp[j-1];
            }
        }
        int answer = dp[n];
        return answer;
    }
}
