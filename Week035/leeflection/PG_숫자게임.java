package studygroup.Week032;
import java.util.*;
public class PG_숫자게임 {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> aq = new PriorityQueue<>();
        PriorityQueue<Integer> bq = new PriorityQueue<>();
        for(int i=0; i<A.length; i++){
            aq.add(A[i]);
            bq.add(B[i]);
        }
        int count = 0;
        while(!aq.isEmpty()){
            int a = aq.poll();
            while(!bq.isEmpty()){
                if(bq.poll() > a){
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
