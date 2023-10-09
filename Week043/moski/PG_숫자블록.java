import java.util.*;
public class PG_숫자블록 {
    public List<Integer> solution(long begin, long end) {
        // N : 10_000_000

        List<Integer> answer = new ArrayList<>();

        int size = (int) (end - begin + 1);

        for(long i = begin; i <= end; i++){
            if(i == 1) {
                answer.add(0);
                continue;
            }
            else{
                // 2번째로 큰 약수 구하기
                long data = 1;
                for(long j = 2; j<=Math.sqrt(i) ; j++){
                    // 2부터 나눠서 딱 떨어진다면
                    if(i % j == 0){
                        if(i / j <= 10_000_000){
                            data = i/j;
                            break;
                        }else data = j;

                    }
                }
                answer.add((int) data);
            }
        }
        return answer;
    }
}
