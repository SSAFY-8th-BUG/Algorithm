package studygroup.Week27;

public class PG_택배배달과수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int D = 0;
        int P = 0;

        for(int i=n-1; i>=0; i--){
            int d = 0;
            int p = 0;

            //앞서 처리된 수량
            if(deliveries[i] - D > 0){
                d = deliveries[i] -D;
                D = 0;
            }else{
                d = 0;
                D -= deliveries[i];
            }

            if(pickups[i] - P > 0){
                p = pickups[i] - P;
                P = 0;
            }else{
                p = 0;
                P -= pickups[i];
            }
            //처리량이 둘중 하나라도 존재하면 가야함
            int count = 0;
            while(!(d<=0 && p <=0)){
                d -= cap;
                p -= cap;
                count++;
            }
            D += Math.abs(d);
            P += Math.abs(p);
            answer += (i+1) * 2 * count;
        }
        return answer;
    }
}
