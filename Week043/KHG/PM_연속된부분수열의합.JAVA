class Solution {
    static int[][] dp; //sum, j
    static int len, K;
    static int[] seq, answer;
    public int[] solution(int[] sequence, int k) {
        
        len = sequence.length;
        answer = new int[]{0,len};
        seq = sequence;
        K=k;
        dp = new int[len][2];
        makeDP0();
        makeDP();
        return answer;
    }
    
    void makeDP0(){ //dp 0번째 값넣기
        int sum=0;
        for(int i=0; i<len; i++){
            if(sum+seq[i] > K){
                dp[0][0] = sum;
                dp[0][1] = i-1;
                return;
            }else if(sum+seq[i] == K){
                answer[0]=0;
                answer[1]=i;
                dp[0][0] = K;
                dp[0][1] = i;
                return;
            }
            sum+=seq[i];
        }
    }
    
    void makeDP(){ //dp채우며 answer구하기
        for(int i=1; i<len; i++){
            if(dp[i-1][0]==0)break;
            int sum = dp[i-1][0]-seq[i-1];
            for(int j=dp[i-1][1]+1; j<len; j++){
                if(sum+seq[j] > K){
                    dp[i][0] = sum;
                    dp[i][1] = j-1;
                    break;
                }else if(sum+seq[j] == K){
                    if(answer[1]-answer[0] > j-i){
                        answer[0] = i;
                        answer[1] = j;
                    }else if(answer[1]-answer[0] == j-i && answer[0]>i){
                        answer[0] = i;
                        answer[1] = j;
                    }
                    dp[i][0] = K;
                    dp[i][1] = j;
                    break;
                }
                sum+=seq[j];
            }
            
        }
    }
}