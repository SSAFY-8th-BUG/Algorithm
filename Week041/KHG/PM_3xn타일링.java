class Solution {
    
    public int solution(int n) {
        int answer = 0;
        long[] dp = new long[2501];
        int mod=1_000_000_007;
        n=n/2;
        dp[1]=3;
        dp[2]=11;
        long unique=2L;
        for(int i=3; i<=n; i++){
            unique += (2*dp[i-2])%mod;
            dp[i] = ((dp[i-1]*3)%mod +unique)%mod;
        }
        answer = (int)dp[n];
        return answer;
    }
}