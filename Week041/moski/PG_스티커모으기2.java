
public class PG_스티커모으기2 {
    public int solution(int sticker[]) {
        int answer = 0;

        if(sticker.length == 1) return sticker[0];
        // 처음 스티커를 떼느냐. 떼지 않느냐 싸움..?
        int[] dp = new int[sticker.length];

        // 처음 스티커를 떼는 경우
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        // 처음을 떼면 마지막껀 못쓰니까 그 전까지만 체크
        for(int i=2;i<sticker.length-1;i++){
            // 이전꺼랑 전전꺼에 스티커 붙였을 때랑 비교해서 큰 값 넣기
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        answer = Math.max(answer, dp[sticker.length-2]);
        // 두번째 스티커를 떼는 경우
        dp = new int[sticker.length];
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i=2;i<sticker.length;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }

        answer = Math.max(answer, dp[sticker.length-1]);

        return answer;
    }
}
