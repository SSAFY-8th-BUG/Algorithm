import java.util.*;

class Solution {
    static int[][] dp;
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        dp = new int[len][3];
        int max=0;
        for(int i=0; i<len; i++){
            int now = sticker[i];
            if(i==0){
                dp[i]=new int[]{now,0,0};
            }else if(i==1){
                dp[i]=new int[]{0,now,0};
            }else if(i==2){
                dp[i]=new int[]{now+dp[0][0],0,now};
            }
            else if(i==len-1){
                dp[i] = new int[]{dp[i-1][0],dp[i-1][1], dp[i-1][2]};
                
                for(int j=2; j<=3; j++){
                    if(i-j<0)break;
                    dp[i][1] = Math.max(dp[i][1], dp[i-j][1]+now);
                    dp[i][2] = Math.max(dp[i][2], dp[i-j][2]+now);
                }
            }
            else{
                dp[i] = new int[]{dp[i-1][0],dp[i-1][1], dp[i-1][2]};
                for(int j=2; j<=3; j++){
                    if(i-j<0)break;
                    dp[i][0] = Math.max(dp[i][0], dp[i-j][0]+now);
                    dp[i][1] = Math.max(dp[i][1], dp[i-j][1]+now);
                    dp[i][2] = Math.max(dp[i][2], dp[i-j][2]+now);
                }
            }
            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
            max = Math.max(max, dp[i][2]);
        }
        
        /*for(int i=0; i<len; i++){
            System.out.println(Arrays.toString(dp[i]));
        }*/
        
        return max;
    }
}