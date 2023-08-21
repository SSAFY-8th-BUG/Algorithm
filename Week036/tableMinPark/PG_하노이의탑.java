import java.util.*;

class PG_하노이의탑 {
    static List<int[]> answer;
    public int[][] solution(int n) {
        answer = new ArrayList<>();

        solve(n, 1, 3, 2);

        int[][] result = new int[answer.size()][2];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
    static void solve(int n, int start, int end, int mid) {
        if (n == 1) {
            answer.add(new int[]{start, end});
        } else {
            solve(n - 1, start, mid, end);
            solve(1, start, end, mid);
            solve(n - 1, mid, end, start);
        }
    }
}