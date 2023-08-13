import java.util.*;

// 구현으로 돌리면 시간초과 100%
// 이분 탐색으로 최종 시간을 점차 줄여가면서 탐색해야 한다.
class PG_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long left = 0;
        long right = (long) n * times[times.length - 1];

        while(left <= right) {
            long mid = (left + right) / 2;
            // 전체 심사한 인원
            long count = 0;
            // 인원 수 계산
            for (int time : times) {
                count += mid / time;
            }
            // 인원 수가 n보다 작으면 시간 증가
            if (count < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}