import java.util.*;

// limit 를 사용하지 않고 두개의 큐 중 하나가 빌 때까지 돌려서 8번 28번 시간초과남
// 시간 생각 좀 잘하자 
public class PG_2_tableMinPark {
    public static void main(String[] args) {
        int[] q1 = {1, 2, 1, 2};
        int[] q2 = {1, 10, 1, 2};

        System.out.println(solution(q1, q2));        
    }

    static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        long s1 = 0, s2 = 0;
        for (int i = 0; i < queue1.length; i++){
            q1.offer(queue1[i]);
            s1 += queue1[i];
            q2.offer(queue2[i]);
            s2 += queue2[i];
        }


        long sum = s1 + s2;
        int p1 = 0, p2 = 0, limit = q1.size() + q2.size();
        if (sum % 2 == 1) return -1;
        while(p1 <= limit && p2 <= limit){
            if (s1 == sum / 2) return answer;
            if (s1 > sum / 2) {
                s1 -= q1.peek();
                s2 += q1.peek();
                q2.add(q1.poll());
                p1++;
            } else {
                s2 -= q2.peek();
                s1 += q2.peek();
                q1.add(q2.poll());
                p2++;
            }
            answer++;
        }
        return -1;
    }
}
