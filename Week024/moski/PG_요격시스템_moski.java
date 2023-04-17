package Week024.moski;
import java.util.*;

public class PG_요격시스템_moski {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets, (t1, t2) -> t1[1]-t2[1]);
        int lastE = targets[0][1] - 1;

        for(int i=1;i<targets.length;i++){
            if(lastE < targets[i][1] && lastE >= targets[i][0]) continue;
            answer++;
            lastE = targets[i][1] -1;
        }
        return answer;
    }
}
