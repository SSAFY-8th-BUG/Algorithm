package studygroup.Week032;

import java.util.ArrayList;

public class PG_하노이의탑 {
    private static ArrayList<int[]> arr = new ArrayList<>();

    public int[][] solution(int n) {
        move(n, 1, 2, 3);
        int[][] answer = arr.stream()
                .toArray(int[][]::new);
        return answer;
    }

    private static void move(int cnt, int start, int mid, int end) {
        if (cnt == 0) {
            return;
        }
        //출발지에서 경유지까지 옮긴다.
        move(cnt - 1, start, end, mid);
        arr.add(new int[]{start, end});
        //경유지에서 목적지까지 옮긴다.
        move(cnt - 1, mid, start, end);
    }
}
