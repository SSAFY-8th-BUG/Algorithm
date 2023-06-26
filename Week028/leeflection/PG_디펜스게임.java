package studygroup.Week28;
import java.util.*;
public class PG_디펜스게임 {
    static int[] e;
    static int len;
    static int ans = Integer.MIN_VALUE;
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p1,p2)->{
            return p2-p1;
        });
        for(int i=0; i<enemy.length; i++){
            pq.add(enemy[i]);
            if(n - enemy[i] < 0){
                if(k > 0){
                    k--;
                    n+= pq.poll();
                }else{
                    return i;
                }
            }
            n -= enemy[i];

        }
        return enemy.length;
    }
}
