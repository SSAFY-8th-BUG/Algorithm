import java.util.*;
public class PG_도둑질 {
    public int solution(int[] money) {
        int answer = 0;
        int[] dp = new int[money.length+1];

        // 첫집을 털 때
        dp[0] = money[0];
        dp[1] = money[0];
        // 마지막 집은 못 털어요
        for(int i=2;i<money.length-1;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        answer = Math.max(answer, dp[money.length-2]);

        dp = new int[money.length+1];

        // 두번째 집부터 털 때
        dp[0] = 0;
        dp[1] = money[1];

        for(int i=2;i<money.length;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }

        answer = Math.max(answer, dp[money.length-1]);

        return answer;
    }
}
