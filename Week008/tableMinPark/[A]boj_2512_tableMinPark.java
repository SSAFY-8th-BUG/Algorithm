import java.io.*;
import java.util.*;

/*
    풀이방법 : left 0, right M -> mid = left + (right - left) / 2 
                를 이용해 조금씩 범위를 줄여나감

    13468KB	    128ms
 */

public class boj_2512_tableMinPark{

    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        int answer = 0;
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }

        M = Integer.parseInt(br.readLine());

        int left = 0;
        int right = M;

        answer = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int sum = 0;
            for (int a : arr){
                if (sum > M) break;
                sum += Math.min(mid, a);
            }

            if (sum <= M) answer = Math.max(mid, answer);

            if (sum > M) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println(Math.min(answer, max));
        br.close();
    }
}

/*
    풀이방법 : 최대값부터 -1 하면서 내려감

    19464KB	    492ms
 */

// import java.io.*;
// import java.util.*;

// public class boj_2512_tableMinPark{

//     static int N, M, answer;
//     static int[] arr;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         N = Integer.parseInt(br.readLine());
//         arr = new int[N];

//         int answer = 0;
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         for (int i = 0; i < N; i++) {
//             arr[i] = Integer.parseInt(st.nextToken());
//             answer = Math.max(arr[i], answer);
//         }

//         M = Integer.parseInt(br.readLine());

//         int sum = 0;
//         while(true) {
//             sum = 0;
//             for (int i = 0; i < N; i++){
//                 if (sum > M) break;
//                 if (arr[i] < answer) sum += arr[i];
//                 else sum += answer;
//             }
//             if (sum > M) answer--;
//             else break;
//         }

//         System.out.println(answer);
//         br.close();
//     }
// }