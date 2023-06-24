import java.util.*;
class Solution {
    static int N, K, len, answer;
    static int[] enemys;
    static PriorityQueue<Integer> hp;
    public int solution(int n, int k, int[] enemy) {
        N=n; K=k; enemys=enemy.clone();
        len = enemy.length;
        answer=len;
        hp = new PriorityQueue<>((o1,o2)->o2-o1);
        solution();
        return answer;
    }
    
    void solution(){
        for(int i=0; i<len; i++){
            int enemy = enemys[i];
            hp.add(enemy);
            if(enemy>N){
                if(K>0){
                    int max = hp.poll();
                    N+=(max-enemy);
                    K--;
                }else{
                    answer = i;
                    return;
                }
            }else{
                N-=enemy;    
            }
            //System.out.println(i+" "+N +" "+K);
            
        }
    }
    
}