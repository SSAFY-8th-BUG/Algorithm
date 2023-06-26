package Week028.moski;

import java.util.*;
public class PG_디펜스게임_moski {
    public int solution(int n, int k, int[] enemy) {
        // 다 더해버리고 최고값들 빼버리면 되나..?
        int answer = 0;
        // 높은 순으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);

        int soldier = n;
        int defense = k;

        for(int i=0; i<enemy.length; i++){
            // 매순간 넣기
            pq.add(enemy[i]);
            soldier -= enemy[i];

            if(soldier < 0){
                if(!pq.isEmpty() && defense > 0){
                    defense--;
                    soldier += pq.poll();
                }else{
                    break;
                }
            }
            answer++;
        }

        return answer;
    }
}
