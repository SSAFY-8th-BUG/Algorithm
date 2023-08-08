import java.util.*;
public class PG_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long min = 1; // 최소 시간
        long max = (long) times[times.length-1] * n; // 최대 시간
        long mid = 0;
        long cnt = 0;

        while(min <= max){
            mid = (min + max) / 2; // 이번에 걸릴 예상 시간
            cnt = 0;

            for(int i=0;i<times.length;i++){
                cnt += mid / times[i];
            }

            // 심사한 인원이 해야할 인원보다 적은 경우
            if(cnt < n){
                min = mid + 1;
            }else{ // 심사한 인원이 해야할 인원보다 크거나 같은 경우
                max = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}
