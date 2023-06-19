// 그리디
class PG_택배배달과수거하기_tableMinPark {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int de = 0, pi = 0;

        for (int i = n - 1; i >= 0; i--){
            if (deliveries[i] != 0 || pickups[i] != 0){
                int cnt = 0;
                // 현재여유공간에 다 담을 수 있도록 여유공간 확보
                while(de < deliveries[i] || pi < pickups[i]){
                    // 왕복횟수 + 1
                    cnt++;
                    de += cap;
                    pi += cap;
                }
                de -= deliveries[i];
                pi -= pickups[i];
                answer += (i + 1) * cnt * 2;
            }
        }
        return answer;
    }
}