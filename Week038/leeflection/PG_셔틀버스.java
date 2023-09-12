package studygroup.Week038;
import java.util.*;

public class PG_셔틀버스 {
        static class Info implements Comparable{
            int hour,minute;
            public Info(int hour, int minute){
                this.hour = hour;
                this.minute = minute;
            }
            public void plus(int m){
                this.minute += m;
                if(this.minute >= 60){
                    this.hour += 1;
                    this.minute %= 60;
                }
            }
            public void minus(int m){
                this.minute -= m;
                if(this.minute < 0){
                    this.hour -= 1;
                    this.minute += 60;
                }
            }
            public String toString(){
                String ret = "";
                String hourS = String.valueOf(hour);
                String minuteS = String.valueOf(minute);
                if(hourS.length() == 1){
                    ret += "0";
                    ret += hourS;
                }else{
                    ret += hourS;
                }
                ret += ":";
                if(minuteS.length()==1){
                    ret += "0";
                    ret += minuteS;
                }else{
                    ret += minuteS;
                }
                return ret;
            }
            public int compareTo(Object l){
                Info o = (Info)l;
                if(this.hour == o.hour){
                    return this.minute - o.minute;
                }else{
                    return this.hour - o.hour;
                }
            }
        }
        public String solution(int n, int t, int m, String[] timetable) {
            ArrayList<Info> list = new ArrayList<>();
            for(String s : timetable){
                String[] tmp = s.split(":");
                int h = Integer.parseInt(tmp[0]);
                int mi = Integer.parseInt(tmp[1]);
                list.add(new Info(h,mi));
            }
            Collections.sort(list);
            //버스 생성
            Info bus = new Info(9,0);
            //마지막 셔틀버스 운행 전까지 옮긴 사람들 제거
            for(int i=0; i<n-1; i++){
                for(int j=0; j<m; j++){
                    if(list.size() > 0 && bus.compareTo(list.get(0)) >= 0){
                        list.remove(0);
                    }
                }
                bus.plus(t);
            }
            //애초에 못타는 사람 제거
            for(int i=0; i<list.size(); i++){
                if(bus.compareTo(list.get(i)) < 0){
                    list.remove(i);
                    i--;
                }
            }

            Info ans = null;
            //마지막 순간 커트라인 사람보다 1분이라도 빨리오면 탈수있음
            if(list.size() >= m){
                list.get(m-1).minus(1);
                ans = list.get(m-1);
                //어짜피 탈수있다면 버스시간에 맞춰서 온다
            }else{
                ans = bus;
            }
            return ans.toString();
        }
}
