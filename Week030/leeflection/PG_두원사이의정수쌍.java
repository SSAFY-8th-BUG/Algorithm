package studygroup.Week030;

public class PG_두원사이의정수쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;
        long x1 = (long)Math.pow(r1,2);
        long x2 = (long)Math.pow(r2,2);
        long side =0;

        for(long i=0;i<=r2;i++){
            long y1 = (long)Math.sqrt(x1-(long)Math.pow(i,2));
            long y2 = (long)Math.sqrt(x2-(long)Math.pow(i,2));
            if(Math.sqrt((x1-Math.pow(i,2)))%1==0){
                side++;
            }
            answer+=(y2-y1)*4;
        }

        answer+=side*4-4;

        return answer;
    }
}
