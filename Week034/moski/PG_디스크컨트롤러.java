package Week034.moski;

import java.util.*;
public class PG_디스크컨트롤러 {
    static int N, minAvg;
    static int[] tmp;
    static boolean[] visit;

    public int solution(int[][] jobs) {
        int answer = 0;
        N = jobs.length;

        int time = 0;
        int end = 0;
        int idx = 0;

        Arrays.sort(jobs, (o1, o2)->o1[0] - o2[0]);

        // 작업이 빨리 끝나는 친구를 먼저 쳐내는게 맞다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        while(time < N){

            while(idx < N && jobs[idx][0] <= end){
                pq.add(jobs[idx]);
                idx++;
            }

            // 비었으면 뒤로 땡겨서 idx의 end를 카운트;
            if(pq.isEmpty()){
                end = jobs[idx][0];
            }else{
                // 가장 짧은 일 먼저 찾기
                int[] tmp = pq.poll();
                answer += tmp[1] + end - tmp[0];
                end += tmp[1];
                time++;
            }
        }
        return answer / N;
    }
}
