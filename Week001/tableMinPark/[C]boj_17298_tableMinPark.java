import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_17298_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());      // 입력하여 배열에 저장

        for (int i = 0; i < N; i++){
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]){  // stack에 있는 인덱스 (아직 오큰수를 찾지 못한 인덱스)와 현재 인덱스의 수를 비교하여
                                                                    // stack에 있는 인덱스의 수보다 현재 인덱스의 수가 더 크면 
                arr[stack.pop()] = arr[i];                          // 인덱스를 꺼내 현재 인덱스의 수로 바꿔준다.
            }
            stack.push(i);      // 현재 인덱스를 push
        }

        while(!stack.isEmpty()) arr[stack.pop()] = -1;      // 남은 인덱스들은 오큰수를 찾지 못했기 때문에 전부다 -1로 바꿔준다.

        for (int a : arr) sb.append(a).append(" ");

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
