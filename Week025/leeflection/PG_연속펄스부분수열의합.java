package studygroup.week25;
import java.util.*;
public class PG_연속펄스부분수열의합 {
    public long solution(int[] sequence) {
        long answer = 0;
        int[] startMinus = new int[sequence.length];
        for(int i=0; i<sequence.length; i++){
            if(i%2==0){
                startMinus[i] = sequence[i]*-1;
            }else{
                startMinus[i] = sequence[i];
            }
        }
        long[] sm = new long[sequence.length+1];
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        for(int i=1; i<sequence.length+1; i++){
            sm[i] = startMinus[i-1]+sm[i-1];
        }
        Arrays.sort(sm);

        return sm[sm.length-1] - sm[0];
    }
}
