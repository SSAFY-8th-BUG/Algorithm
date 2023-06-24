package Week027.moski;

public class PG_택배배달과수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0;
        int pickup = 0;
        for (int i = n-1; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) {
                int cnt = 0;

                while (deliver < deliveries[i] || pickup < pickups[i]) {
                    // 몇 번 방문할지 카운트
                    cnt++;
                    deliver += cap;
                    pickup += cap;
                }
                // 남은 트럭 자리 계산
                deliver -= deliveries[i];
                pickup -= pickups[i];
                answer += (i+1) * cnt * 2;
            }
        }
        return answer;
    }
}
