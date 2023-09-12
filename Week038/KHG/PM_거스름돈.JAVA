class Solution {
    static int[] dp;
    public int solution(int n, int[] money) {
        dp = new int[n+1];
        dp[0]=1;
        for(int m:money){
            for(int i=1; i<=n; i++){
                int j=i-m;
                if(j<0)continue;
                dp[i]+=dp[j];
            }
        }
        
        int answer = dp[n];
        return answer;
    }
}