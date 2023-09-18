package studygroup.Week040;
import java.util.*;
public class PG_디스크컨트롤러 {
    static class Job{
        int time;
        int val;
        int end;
        public Job(int time, int val){
            this.time = time;
            this.val = val;
        }
    }
    public int solution(int[][] jobs){
        PriorityQueue<Job> pq = new PriorityQueue<>((o1,o2)->{
            return o1.time - o2.time;
        });

        for(int i=0; i<jobs.length; i++){
            pq.add(new Job(jobs[i][0],jobs[i][1]));
        }

        PriorityQueue<Job> tmp = new PriorityQueue<>((o1,o2)->{
            return o1.end - o2.end;
        });

        int sum = 0;
        int time = pq.peek().time;
        //하나씩 뽑는다
        while(!pq.isEmpty()){
            //현재 진행중인 일이 끝나기 전에 들어온 일들
            while(!pq.isEmpty() && time >= pq.peek().time){
                Job j = pq.poll();
                j.end = j.val + time;
                tmp.add(j);
            }
            //일이 끝나기 전에 들어온 일이 없을때
            if(tmp.isEmpty()){
                Job k = pq.poll();
                time = k.time + k.val;
                sum += k.val;
            }else{
                Job k = tmp.poll();
                sum += k.val + (time - k.time);
                time += k.val;
                while(!tmp.isEmpty()){
                    pq.add(tmp.poll());
                }
            }
        }
        return (int)(sum/jobs.length);
    }
}
