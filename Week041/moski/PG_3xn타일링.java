public class PG_3xn타일링 {
    public int solution(int n) {
        int answer = 0;
        int mod = 1_000_000_007;
        long[] dp = new long[n+1];
        if(n%2 == 1) return 0;

        dp[0] = 1;
        dp[2] = 3;

        for(int i=4;i<n+1;i+=2){
            dp[i] = dp[i-2] * 3;
            for(int j=i-4;j>=0;j-=2){
                dp[i] += dp[j] * 2;
            }
            dp[i] %= mod;
        }

        answer = (int) dp[n];
        return answer;
    }
}
