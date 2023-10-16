package studygroup.Week044;
import java.util.*;
public class PG_요격시스템 {
    static class Missile{
        int start;
        int end;
        public Missile(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int[][] targets) {
        int answer = 0;
        ArrayList<Missile> list = new ArrayList<>();
        for(int i=0; i<targets.length; i++){
            int x = targets[i][0];
            int y = targets[i][1];
            Missile missile = new Missile(x,y);
            list.add(missile);
        }
        Collections.sort(list,(p1,p2)->{
            if(p1.end == p2.end){
                return p1.start - p2.start;
            }
            return p1.end- p2.end;
        });

        int end = list.get(0).start;

        for(Missile m : list){
            if(end <= m.start){
                System.out.println(m.start+" "+m.end);
                answer++;
                end = m.end;
            }
        }

        return answer;

    }
}
