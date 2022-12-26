package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_문자열변환_10453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            List<Integer> idx_list_A = new ArrayList<>();
            List<Integer> idx_list_B = new ArrayList<>();
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) == 'a')
                    idx_list_A.add(i);
                if (B.charAt(i) == 'a')
                    idx_list_B.add(i);
            }
            Collections.sort(idx_list_A);
            Collections.sort(idx_list_B);
            int ans = 0;
            for (int i = 0; i < idx_list_A.size(); i++) {
                if (idx_list_A.get(i) != idx_list_B.get(i))
                    ans += Math.abs(idx_list_A.get(i) - idx_list_B.get(i));
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }
}