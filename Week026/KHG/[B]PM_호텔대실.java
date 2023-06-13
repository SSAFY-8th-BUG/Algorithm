import java.util.*;
class Solution {
    static int [][] book_times;
    static int len, answer;
    static List<Integer> endTimes;
    public int solution(String[][] book_time) {
        answer = 0;
        init(book_time);
        count();
        
        return answer;
    }
    
    static void init(String[][] book_time){
        len = book_time.length;
        book_times = new int[len][2];
        for(int i=0; i<len; i++){
            for(int j=0; j<2; j++){
                String[] times = book_time[i][j].split(":");
                int h = Integer.parseInt(times[0]);
                int m = Integer.parseInt(times[1]);
                int time = h*60 + m;
                book_times[i][j]=time;
            }
        }
        Arrays.sort(book_times, (o1,o2)-> o1[0]-o2[0]);
        endTimes = new ArrayList<>();
    }
    
    static void count(){
        answer=1;
        endTimes.add(book_times[0][1]+10);
        for(int i=1; i<len; i++){
            int st = book_times[i][0];
            int et = book_times[i][1];
            
            boolean needRoom = true;
            for(int j=0; j<answer; j++){
                if(endTimes.get(j)<=st){
                    endTimes.set(j,et+10);
                    needRoom=false;
                    break;
                }
            }
            if(needRoom){ //새방이 필요한경우
                endTimes.add(et+10);
                answer++;
            }
            //System.out.println(endTimes);
        }
    }
}