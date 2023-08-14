package studygroup.Week032;
import java.util.*;
public class PG_입국심사 {
        public long solution(int n, int[] times) {
            Arrays.sort(times);
            long lt = 0;
            long rt = (long) times[times.length - 1] * n;
            long ans = 0;
            while (lt <= rt) {
                long mid = (lt + rt) / 2;
                if (isPossible(n, times, mid)) {
                    rt = mid - 1;
                    ans = mid;
                } else {
                    lt = mid + 1;
                }
            }

            return ans;
        }

        static public boolean isPossible(int n, int[] times, long mid) {
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }
            if (sum < n) {
                return false;
            } else {
                return true;
            }
        }
}
