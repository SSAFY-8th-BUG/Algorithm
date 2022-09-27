import java.io.*;
import java.util.*;

public class swea_4008_tableMinPark {

    static int N, T, max, min;
    static int[] nums;
    static int[] calc;
    static char[] c = {'+', '-', '*', '/'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            calc = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                calc[i] = Integer.parseInt(st.nextToken());
            }

            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

            tgt = new char[N - 1];
            v = new boolean[N - 1];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            perm(0, nums[0]);
            
            sb.append("#").append(t).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static char[] tgt;
    static boolean[] v;

    static void perm(int count, int sum){
        if (count == N - 1) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (calc[i] == 0) continue;
            int next = sum;
            int b = nums[count + 1];
            switch(c[i]) {
                case '+': next += b; break;
                case '-': next -= b; break;
                case '*': next *= b; break;
                case '/': next /= b; break;
            }
            calc[i]--;
            perm(count + 1, next);
            calc[i]++;
        }
    }
}