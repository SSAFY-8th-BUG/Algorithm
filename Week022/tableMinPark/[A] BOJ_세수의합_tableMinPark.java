import java.io.*;
import java.util.*;

public class BOJ_세수의합_tableMinPark {

    static int N;
    static int[] U;
    static List<Integer> S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        U = new int[N];
        
        for (int i = 0; i < N; i++) U[i] = Integer.parseInt(br.readLine());

        S = new ArrayList<>();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                S.add(U [i] + U[j]);
            }
        }

        Arrays.sort(U);
        Collections.sort(S);

        int answer = 0;
        for (int i = N - 1; i >= 0; i--){
            for (int j = 0; j < i; j++){
                int g = U[i] - U[j];

                if (solve(g) && U[i] > answer){
                    answer = U[i];
                }
            }
        }

        System.out.println(answer);
        br.close();
    }

    static boolean solve(int n){
        int head = 0;
        int tail = S.size() - 1;
        int mid = 0;

        while(head < tail){
            mid = (head + tail) / 2;

            if (S.get(mid) < n){
                head = mid + 1;
            } else if (S.get(mid) > n){
                tail = mid - 1;
            } else if (S.get(mid) == n){
                return true;
            }
        }
        return false;
    }
}