import java.io.*;
import java.util.*;

public class bj_5430_tableMinPark {

    static int T;
    static char[] p;
    static Deque<Integer> q;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

LOOP:   for (int t = 0; t < T; t++){
            p = br.readLine().toCharArray();
            
            int N = Integer.parseInt(br.readLine());

            String input = br.readLine();
            String[] numbers = input.substring(1, input.length() - 1).split(",");

            q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                q.push(Integer.parseInt(numbers[i]));
            }

            boolean front = true;
            for (char next : p) {
                if (next == 'R') front = !front;
                if (next == 'D') {
                    if (q.isEmpty()) {
                        sb.append("error").append("\n");
                        continue LOOP;
                    }
                    if (!front) q.pollFirst();
                    else q.pollLast();
                }
            }

            sb.append("[");
            while(!q.isEmpty()) {
                if (!front) sb.append(q.pollFirst());
                else sb.append(q.pollLast());
                if (q.size() > 0) sb.append(",");
            }
            sb.append("]\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}
