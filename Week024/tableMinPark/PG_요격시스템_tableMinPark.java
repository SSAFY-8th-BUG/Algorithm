import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (int[] t1, int[] t2) -> t1[1] - t2[1]);
        
        int e = targets[0][1];
        answer++;
        
        for (int[] target : targets) {
            // ???? ?? ??? ??? ????? ???? ??? 
            // ??? ?? + ?? ??
            if (target[0] >= e) {
                e = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}