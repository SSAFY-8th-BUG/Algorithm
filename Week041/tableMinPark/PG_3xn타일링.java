class Solution {
    static int MOD = 1_000_000_007;

    public long solution(int n) {
        long[] dp = new long[5001];
        dp[0] = 1;
        dp[2] = 3;

        // fn(n) = fn(n - 2) - fn(n - 4)
        for (int now = 4; now <= n; now += 2) {
            dp[now] = ( (dp[now - 2] * 4 % MOD) - (dp[now - 4] % MOD) + MOD ) % MOD;
        }

        return dp[n];
    }
}