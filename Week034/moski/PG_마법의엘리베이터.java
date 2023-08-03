public class PG_마법의엘리베이터 {
    public int solution(int storey) {
        int answer = 0;
        int floor = storey;

        while(floor > 0){
            int masic = floor % 10;
            if(masic >= 0 && masic <= 4){
                // 0~4 사이면 -1을 그만큼
                answer += masic;
                floor = floor/10;
            }else if(masic == 5){
                // 5면 앞자리 보고 판단
                // 4 이하면 빼고 5이상이면 더하고
                // 만약 젤 앞자리 5면 그대로 더한다.
                if(floor < 10){
                    answer += masic;
                    floor = floor/10;
                }else{
                    // 5가 아닌경우
                    int tmp = floor/10;

                    if(tmp%10 < 5){
                        answer += 5;
                        floor = floor/10;
                    }else{
                        answer += 5;
                        floor = floor/10;
                        floor++;
                    }
                }
            }
            else{
                // 6~9 면 10에서 뺀 값(+1)을 더함
                answer += 10 - masic;
                floor = floor/10;
                // 그만큼 더했으니까
                floor++;
            }
        }
        return answer;
    }
}
