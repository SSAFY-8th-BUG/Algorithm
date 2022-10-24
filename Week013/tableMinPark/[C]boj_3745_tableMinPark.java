import java.io.*;
import java.util.*;

// 브루트포스로 이전의 값들을 탐색하면 시간초과
// 이분탐색을 통해 값을 탐색해야함
public class boj_3745_tableMinPark {

    static int N, len;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while ((input = br.readLine()) != null){
            N = Integer.parseInt(input.trim());

            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            dp = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dp[i] = Integer.MAX_VALUE;
            }

            len = 0;
            for (int i = 0; i < N; i++){
                int position = Arrays.binarySearch(dp, 0, len, arr[i]);

                // 같은 것이 있으면 패스
                if (position >= 0) continue;

                // 같은 것이 없으면 원래 들어가야할 자리의 인덱스가 음수로 주어짐
                // [1, 2, 3, 4, 5] -> 0 을 찾는 경우 -> -1 의 값이 리턴됨 (자신보다 가장 가까운 큰 수의 인덱스를 음수로 바꾸고 -1 을 한 값)
                position = Math.abs(position) - 1;
                // 가장 가까운 큰 수의 자리에 현재의 수와 들어가있는 수를 비교하여 작은 값을 넣음
                dp[position] = Math.min(dp[position], arr[i]);
                if(position == len) len++;  // dp 의 마지막 인덱스라면 길이를 하나 증가
            }
            sb.append(len).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}
