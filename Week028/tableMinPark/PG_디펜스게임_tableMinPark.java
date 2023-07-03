class PG_디펜스게임_tableMinPark_재귀 {
    static int E, answer;

    public int solution(int n, int k, int[] enemy) {
        answer = 0;
        E = enemy.length;
        dfs(n, k, 0, enemy);

        return answer;
    }

    void dfs(int n, int k, int d, int[] enemy){
        // 마지막 라운드 or 더이상 진행할 수 없을 때
        if (d == E || (n < enemy[d] && k == 0)){
            answer = Math.max(answer, d);
            return;
        }

        if (n >= enemy[d]) {
            dfs(n - enemy[d], k, d + 1, enemy);
        }
        if (k > 0) {
            dfs(n, k - 1, d + 1, enemy);
        }
    }
}

import java.util.*;
class PG_디펜스게임_tableMinPark {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        int count = 0;
        int round = 0;

        for (round = 0; round < enemy.length; round++){
            int now = enemy[round];
            q.add(now);
            n -= now;

            if (n < 0){
                if (count < k) {
                    n += q.poll();
                    count++;
                } else {
                    break;
                }
            }
        }
        answer = round;

        return answer;
    }
}