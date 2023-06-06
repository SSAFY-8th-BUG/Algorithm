import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        // 앞에서부터 최대 값에 앞에서부터의 최소 값을 빼면 나오나
        // -1,1,-1,1로 하나 1,-1,1-1로 하나 절댓값은 같다

        long max = 0;
        long min = 0;
        long sum = 0;

        // 아 첫 시작이 양수냐 음수냐에 따라 max min이 바뀌는구나
        // 결국 다 0에서 시작하는구나

        for(int i=0; i < sequence.length; i++){

            if(i % 2 == 0){
                sum += sequence[i];
            }else{
                sum += sequence[i] * -1;
            }

            if(max < sum){
                max = sum;
            }

            if(min > sum){
                min = sum;
            }
        }

        answer = Math.abs(max - min);

        return answer;
    }
}