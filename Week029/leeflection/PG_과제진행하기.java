package studygroup.Week029;
import java.util.*;
public class PG_과제진행하기 {
    /**
     새로운 과제를 시작할 시간이 되었을 때 기존에 진행중이던 과제가 있다면 멈추고 새로운 과제를 해야함
     과제를 끝냈을 때 잠시 멈춘 과제가 있다면 멈춰둔 과제를 이어서 진행
     끝낸 시간에 새로 시작해야 되는 과제와 멈춰둔 과제가 있다면 새로 시작해야하는 과제 부터 진행
     **/
        static class Sub{
            int start;
            int remain;
            String name;
            public Sub(int start, int remain, String name){
                this.start = start;
                this.remain = remain;
                this.name = name;
            }
        }
        public String[] solution(String[][] plans) {
            PriorityQueue<Sub> pq = new PriorityQueue<>((p1,p2)->{
                return p1.start - p2.start;
            });

            for(int i=0; i<plans.length; i++){
                String name = plans[i][0];
                int start = Integer.parseInt(plans[i][1].split(":")[0]) * 60 + Integer.parseInt(plans[i][1].split(":")[1]) ;
                int pt = Integer.parseInt(plans[i][2]);
                pq.add(new Sub(start,pt,name));
            }

            Stack<Sub> stack = new Stack<>();
            String[] answer = new String[plans.length];
            int ansi = 0;

            while(!pq.isEmpty()){
                Sub s = pq.poll();
                //pq에 값이 하나 있을때
                if(!pq.isEmpty()){
                    //뽑은 값과 안에있는 값 비교 후 뽑은 값이 끝나는 시간 보다 뒤에 시작시간이 빠른 경우 뽑은거 스택에 넣기
                    if(s.start + s.remain > pq.peek().start){
                        s.remain = s.start + s.remain - pq.peek().start;
                        stack.push(s);
                        // 처리하고도 시간이 남는 경우 배열에 추가하고 남는 시간만큼 스택에 있는 것들을 처리하자
                    }else{
                        int time = pq.peek().start - s.start - s.remain;
                        answer[ansi] = s.name;
                        ansi++;

                        while(!stack.isEmpty()){
                            Sub ss = stack.pop();
                            if(ss.remain > time){
                                ss.remain -= time;
                                stack.push(ss);
                                break;
                            }else{
                                time -= ss.remain;
                                answer[ansi] = ss.name;
                                ansi++;
                            }
                        }
                    }
                    //pq가 비어있다면 바로 집어넣어준다.
                }else{
                    answer[ansi] = s.name;
                    ansi++;
                }
            }
            while(!stack.isEmpty()){
                Sub ss = stack.pop();
                answer[ansi] = ss.name;
                ansi++;
            }

            return answer;
        }
}
