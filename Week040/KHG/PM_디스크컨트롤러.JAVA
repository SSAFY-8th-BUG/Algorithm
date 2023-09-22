import java.util.*;

class Solution {
    static int idx, now, len, answer;
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        len = jobs.length;
        answer = 0;
        
        doWork(jobs);
        answer/=len;
        return answer;
    }
    
    static void doWork(int[][] jobs){
        PriorityQueue<Task> hp = new PriorityQueue<>((o1,o2)->o1.time - o2.time);
        now=0;
        idx=0;
        
        while(idx<len || !hp.isEmpty()){
            while(idx<len && jobs[idx][0]<=now){ //추가가능한거 모두 추가
                hp.add(new Task(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            if(idx<len && hp.size()==0){ //추가할게 없으면
                now = jobs[idx][0];
                continue;
            }
            
            //제일 짧은거
            Task task = hp.poll();
            now +=task.time;
            answer += now-task.start;
            //System.out.println(task.start + " " + now + " "+answer);
            
        }
    }
    
    static class Task{
        int start;
        int time;
        Task(int start, int time){
            this.start = start;
            this.time = time;
        }
    }
}