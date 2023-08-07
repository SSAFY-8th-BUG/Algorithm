import java.util.*;

class PG_여행경로 {
    static int N;
    static String[] trace;
    static boolean[] v;
    static boolean isFin;
    static T[] src;
    static class T {
        String s;
        String e;
        public T (String s, String e) {
            this.s = s;
            this.e = e;
        }
    }

    public String[] solution(String[][] tickets) {
        N = tickets.length;
        src = new T[N];

        for (int i = 0; i < N; i++) {
            String A = tickets[i][0];
            String B = tickets[i][1];
            src[i] = new T(A, B);
        }

        Arrays.sort(src, (t1, t2) -> {
            if (t1.e == t2.e) {
                return t1.s.compareTo(t2.s);
            }
            return t1.e.compareTo(t2.e);
        });

        trace = new String[N + 1];
        trace[0] = "ICN";

        for (int i = 0; i < N; i++) {
            T now = src[i];
            if (now.s.equals("ICN")) {
                v = new boolean[N];
                isFin = false;
                v[i] = true;
                trace[1] = now.e;
                dfs(now.e, 2);
            }
            if (isFin) {
                break;
            }
        }

        return trace;
    }

    static void dfs(String now, int d) {
        if (d == N + 1) {
            isFin = true;
            return;
        }

        for (int i = 0; i < N; i++) {
            T next = src[i];

            if (v[i] || isFin) {
                continue;
            }

            if (now.equals(next.s)) {
                v[i] = true;
                trace[d] = next.e;
                dfs(next.e, d + 1);
                v[i] = false;
            }
        }
    }
}