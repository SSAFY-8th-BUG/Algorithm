public class PG_기지국설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        // 하나의 기지국 당 가질 수 있는 범위 2*w + 1
        // 길이가 n일 때 w의 범위를 가지는 기지국의 최소 설치 수 = n / (2*w + 1)
        // 나머지가 있으면 무조건 + 1
        // 앞에서 부터
        int idx = 1;
        int cnt = 0;
        for(int s : stations){
            // s는 시작점
            // s-w가 idx보다 같거나 작다면 idx를 s+w+1로 옮긴다.
            if(s - w > idx){
                cnt = (s - w) - idx;
                int len = cnt/(2*w + 1);
                if(cnt%(2*w + 1) != 0){
                    len++;
                }
                // answer += search(cnt, w);
                answer += len;
            }
            idx = s + w + 1;
        }
        if(idx <= n){
            cnt = n - idx + 1;
            int len = cnt/(2*w + 1);
            if(cnt%(2*w + 1) != 0){
                len++;
            }
            answer += len;
            // answer += search(cnt, w);
        }

        return answer;
    }

    static int search(int cnt, int w){
        // System.out.println(cnt + " " + w);
        if(cnt%((2*w) + 1) == 0){
            // System.out.println(cnt/(2*w + 1));
            return cnt/(2*w + 1);
        }else{
            // System.out.println(cnt/(2*w + 1) + 1);
            return (cnt/(2*w + 1)) + 1;
        }
    }
}
