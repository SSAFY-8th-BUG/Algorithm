package studygroup.Week029;
import java.util.*;
public class PG_광고삽입 {
    /**
     공익광고를 넣어달라는 요청
     가장 많이 보는 구간에 공익광고를 넣고 싶음
     동영상 길이
     공익광고 길이
     구간 정보를 가짐
     시작시간을 언제로 해야할까 누적 재생시간이 여러 곳이면 가장 빠른 시각을 return
     **/
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "";
            int[] pt = new int[(int)trans(play_time) + 1];
            long advLen = trans(adv_time);
            long val = 0;
            long ans = 0;
            long max = Long.MIN_VALUE;

            for(int i=0; i<logs.length; i++){
                String m[] = logs[i].split("-");
                long start = trans(m[0]);
                long end = trans(m[1]);
                pt[(int)start]++;
                pt[(int)end]--;
            }

            //구간별 시청 인원 배열 만들기
            for(int i=1; i<pt.length; i++){
                pt[i] = pt[i-1]+pt[i];
            }

            //슬라이딩 윈도우
            for(int i=0; i<advLen; i++){
                val += pt[i];
            }

            ans = 0;
            max = Math.max(val,max);

            for(long i=advLen; i<pt.length; i++){
                val += pt[(int)i];
                val -= pt[(int)(i-advLen)];
                if(val > max){
                    max = val;
                    //테케에서 여기 1만큼 비길래 +1 해주니까 통과됨 뭐지?
                    ans = i - advLen +1;
                }
            }
            return iToS(ans);
        }

        static long trans(String t){
            String[] arr = t.split(":");
            return (Long.parseLong(arr[0]) * 3600) + (Long.parseLong(arr[1]) * 60) + Long.parseLong(arr[2]);
        }

        static String iToS(long ans){
            String h = String.format("%02d", ans/3600);
            String m = String.format("%02d", ans%3600/60);
            String s = String.format("%02d", ans%3600%60);
            return h+":"+m+":"+s;
        }
}
