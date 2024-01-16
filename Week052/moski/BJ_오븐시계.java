import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_오븐시계 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(br.readLine());

        // time에서 시간만큼 hour에 넣기
        hour += time/60;

        // time에서 분만 남기기
        time = time%60;

        // 해당 time을 분에 더하는데 만약 59를 넘어간다면 hour에 1더하고 그만큼 분빼기
        if(time + minute >= 60){
            hour++;
            minute = time + minute - 60;
        }else{
            minute += time;
        }

        // 혹시 시간이 24를 넘어간 경우 24로 나눈 나머지를 따진다.
        hour = hour%24;

        System.out.println(hour + " " + minute);


    }
}
