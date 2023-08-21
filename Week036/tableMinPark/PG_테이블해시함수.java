import java.util.*;

class PG_테이블해시함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int col_idx = col - 1;
        int begin_idx = row_begin - 1;
        int end_idx = row_end - 1;

        Arrays.sort(data, (d1, d2) -> {
            if (d1[col_idx] == d2[col_idx]) {
                return d2[0] - d1[0];
            }
            return d1[col_idx] - d2[col_idx];
        });

        int answer = 0;
        for (int i = begin_idx; i <= end_idx; i++) {
            int sum = 0;
            int[] nowArr = data[i];

            for (int now : nowArr) {
                sum += now % (i + 1);
            }

            // bitwise
            if (i == begin_idx) {
                answer = sum;
            } else {
                answer = answer ^ sum;
            }
        }

        return answer;
    }
}