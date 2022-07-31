import java.io.*;
import java.util.*;

// 시간복잡도 ->
// 공간복잡도 -> 
public class boj_2304_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = new int[1001];                  // 범위를 모르기 때문에 1000 크기의 배열을 선언
        int N = Integer.parseInt(br.readLine());

        int front = 0;          // 시작점
        int rear = 0;           // 종료점
        int max = 0;            // 가장 높은 건물의 높이
        int maxIdx = 0;         // 가장 높은 건물의 높이의 인덱스 (같은 높이라면 가장 마지막에 위치한 건물의 인덱스)

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr[L] = H;
            front = Math.min(front, L);     // 시작점을 체크
            rear = Math.max(rear, L);       // 종료점을 체크
            if (H > max){
                maxIdx = L;                 // 가장 높은 건물의 인덱스
                max = H;                    // 가장 높은 건물의 높이
            }
        }

        int answer = 0;
              
        // 앞에서부터 뒤로
        int pastValue = 0;                      // 탐색한 건물 중 가장 높은 건물의 높이
        for (int i = front; i <= maxIdx; i++){  // 시작점부터 가장 높은 건물 인덱스까지 반복
            if (pastValue < arr[i]){            // 탐색한 건물 중 가장 높은 건물의 높이와 비교하여 작으면
                answer += arr[i];               // 현재 위치의 높이를 더하고
                pastValue = arr[i];             // 탐색한 건물 중 가장 높은 건물의 높이를 초기화
            } else answer += pastValue;         // 탐색한 건물 중 가장 높은 건물의 높이와 비교하여 크면 
        }                                       // 탐색한 건물 중 가장 높은 건물의 높이를 더해줌

        // 뒤에서 앞으로
        pastValue = 0;
        for (int i = rear; i > maxIdx; i--){    // 종료점부터 반대로 가장 높은 건물 인덱스 전까지 반복
            if (pastValue < arr[i]){            // 위와 동일한 프로세스
                answer += arr[i];
                pastValue = arr[i];
            } else answer += pastValue;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.flush();
        bw.close();
    }
}