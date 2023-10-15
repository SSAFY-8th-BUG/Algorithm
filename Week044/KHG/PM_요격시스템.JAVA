import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets, (o1,o2)-> o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        
        int ms=targets[0][0];
        int me=targets[0][1];
        for(int i=1; i<targets.length; i++){
            int s = targets[i][0];
            int e = targets[i][1];
            if(s>=me){
                ms=s;
                me=e;
                answer++;
                continue;
            }
            
            ms = Math.max(ms,s);
            me = Math.min(me,e);
            
        }
        return answer;
    }
}