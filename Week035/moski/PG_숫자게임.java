import java.util.*;
public class PG_숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        // PriorityQueue<Integer> pqA = new PriorityQueue<>();
        // PriorityQueue<Integer> pqB = new PriorityQueue<>();

        Arrays.sort(A);
        Arrays.sort(B);
        int idxA = 0;
        int idxB = 0;
        // 1 3 5 7
        // 2 2 5 8
        // 1 1 1

        // B인덱스가 끝까지 갈때까지 체크
        while(idxB < B.length){
            if(A[idxA] < B[idxB]){
                // B가 이기면 다음 인덱스 체크
                idxA++;
                idxB++;
                answer++;
            }else{
                // 그게 아닌 경우 A는 그대로 B를 이동 시켜 이기는 값을 찾는다.
                idxB++;
            }
        }

        return answer;
    }
}
