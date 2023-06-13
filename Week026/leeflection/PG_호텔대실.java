package studygroup.week26;

public class PG_호텔대실 {
    static class Time{
        public Time(int start, int end){
            this.start = start;
            this.end =end;
        }
        int start;
        int end;
    }
    public int solution(String[][] book_time) {
        int answer = -1;
        ArrayList<Time> list = new ArrayList<>();
        for(int i=0; i<book_time.length; i++){
            String[] start = book_time[i][0].split(":");
            int time1 = Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);

            String[] end = book_time[i][1].split(":");
            int time2 = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1])+10;

            list.add(new Time(time1,time2));
        }
        Collections.sort(list,(l1,l2)->{
            return l1.start - l2.start;
        });

        PriorityQueue<Time> pq = new PriorityQueue<>((p1,p2)->{
            return p1.end - p2.end;
        });

        for(Time t : list){

            while(!pq.isEmpty() && pq.peek().end <= t.start){
                if(pq.peek().end <= t.start) pq.poll();
            }
            pq.add(t);
            answer = Math.max(answer, pq.size());

        }
        return answer;
    }
}
