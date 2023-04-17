import java.util.*;
public class PG_요격시스템 {
        /**
         * 폭격 미사일을 요격 해야함
         * 미사일을 최소로 사용해서 모든 폭격 미사일 요격
         * 2차원 공간 a나라에서 발사한 미사일은 x축에 평행한 직선(s,e)
         * b나라는 x,y, 좌표에서 y축에 수평이 되도록 미사일 발사
         * 해당 x 좌표에 걸쳐있는 모든 폭격 미사일을 관통하여 한 번에 요격 가능
         *(s , e) 사이에 값이 있어야 요격 가능
         **/
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
