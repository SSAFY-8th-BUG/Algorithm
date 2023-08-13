import java.util.*;

class PG_숫자게임 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int aIdx = 0;
        int bIdx = 0;
        int answer = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[aIdx] >= B[bIdx]) {
                bIdx++;
            } else {
                aIdx++;
                bIdx++;
                answer++;
            }
        }

        return answer;
    }
}