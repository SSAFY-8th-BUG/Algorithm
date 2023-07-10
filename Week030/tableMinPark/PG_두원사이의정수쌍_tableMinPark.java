import java.util.*;

// 그리디?
// bfs?
// 그냥 수학 (피타고라스)
// long 타입 때문에 1시간 버림
class Solution {
    public long solution(int r1_, int r2_) {
        long answer = 0;

        long r1 = (long) r1_;
        long r2 = (long) r2_;

        for (long i = 1; i <= r2; i++) {
            int start = (int) Math.ceil(Math.sqrt((r1 * r1) - (i * i)));
            int end = (int) Math.floor(Math.sqrt((r2 * r2) - (i * i)));

            answer += end - start + 1;
        }

        return answer * 4;
    }
}