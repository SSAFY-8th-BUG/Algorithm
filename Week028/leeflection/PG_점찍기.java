package studygroup.Week28;

public class PG_점찍기 {
    public static long solution(int k, int d) {
        long answer = 0;

        for(int i=0; i<=d; i+=k){
            long xx = (long) Math.pow(i, 2);
            long dd = (long) Math.pow(d, 2);
            int result = (int)(Math.sqrt(dd-xx));
            answer += (result/k)+1;
        }
        return answer;
    }
}
