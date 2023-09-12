package studygroup.Week038;

public class PG_거스름돈 {

        public int solution(int n, int[] money) {
            int[] dp = new int[n+1];
            dp[0] = 1;
            for(int i=0; i<money.length; i++){
                for(int j=money[i]; j<n+1; j++){
                    dp[j] += dp[j - money[i]] % 1000000007;
                }
            }

            return dp[n];
        }

}
