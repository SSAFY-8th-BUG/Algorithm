package Week028.moski;
import java.util.*;
public class PG_점찍기_moski {
    public long solution(int k, int d) {
        // 반지름이 d인 원ㄴ안에 점이 몇개 잇나.,,(1사분면)
        long answer = 0;
        long h = 0;
        long w = 0;
        for(int i=0; i<=d; i += k){
            h = (long) d*d;
            w = (long) i*i;

            answer += (int)Math.sqrt(h - w)/k + 1;
        }
        return answer;
    }
}
