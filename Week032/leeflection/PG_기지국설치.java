package studygroup.Week031;

public class PG_기지국설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 1;
        int plus = 0;

        for(int i=0; i<stations.length; i++){
            //n이 주어지면
            //n-w까지는 가능
            //n-w가 현재 idx ~ n-w-1까지 범위에 건설하자
            //idx를 n+w+1로 변경
            int start = (stations[i]-w < 1)?1:stations[i]-w;
            if(start > idx){
                int val = (start-idx)/(w*2+1);
                int na = (start-idx)%(w*2+1);
                if(na > 0){
                    val++;
                }
                plus+=val;
            }
            idx=stations[i]+w+1;
        }
        if(idx <= n){
            int end = n+1;
            int mok = (end-idx)/(w*2+1);
            int na = (end-idx)%(w*2+1);
            if(na > 0){
                mok++;
            }
            plus += mok;
        }
        return plus;
    }
}
