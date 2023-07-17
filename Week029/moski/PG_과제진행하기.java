package Week029.moski;

import java.util.*;
public class PG_과제진행하기 {
    static Assignment[] assignments;
    static Deque<Assignment> q = new ArrayDeque<>();
    static List<String> ans = new ArrayList<>();
    static int time;

    public String[] solution(String[][] plans) {

        time = -1;

        assignments = new Assignment[plans.length];
        for(int i=0; i<plans.length;i++){
            Assignment assignment = new Assignment(plans[i][0], plans[i][1], plans[i][2]);
            assignments[i] = assignment;
        }


        Arrays.sort(assignments, (o1, o2) -> o1.start - o2.start);

        for(int i=0;i<assignments.length;i++){
            if(q.isEmpty()){
                q.add(assignments[i]);
                continue;
            }

            Assignment curAss = q.peekLast();
            Assignment newAss = assignments[i];

            time = curAss.start;

            if(time + curAss.playtime <= newAss.start){
                rePop(newAss, time);
            }else{
                curAss.playtime -= newAss.start - time;
            }
            q.add(newAss);
        }

        while(!q.isEmpty()){
            ans.add(q.pollLast().name);
        }

        String[] answer = new String[ans.size()];

        for(int i=0;i<ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }

    static void rePop(Assignment newAss, int time){
        if(!q.isEmpty()){
            Assignment curAss = q.peekLast();
            if(time + curAss.playtime <= newAss.start){
                ans.add(q.pollLast().name);
                rePop(newAss, time + curAss.playtime);
            }else{
                curAss.playtime -= newAss.start - time;
            }
        }
    }

    static class Assignment {
        String name;
        int start;
        int playtime;

        public Assignment(String name, String start, String playtime){
            this.name = name;
            String[] hm = start.split(":");
            int h = Integer.parseInt(hm[0]) * 60;
            int m = Integer.parseInt(hm[1]);
            this.start = h+m;

            this.playtime = Integer.parseInt(playtime);
        }
    }
}
