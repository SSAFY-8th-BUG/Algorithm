
// 분할정복
// y : x : d
// 0 : 0 : 4

// 0 : 0 : 2
// 0 : 0 : 1
// 0 : 1 : 1
// 1 : 0 : 1
// 1 : 1 : 1

// ...

class Solution {

    static int[] dy = {0, 1, 0, 1};
    static int[] dx = {0, 0, 1, 1};
    static int[] answer;

    public int[] solution(int[][] arr) {
        answer = new int[2];

        solve(0, 0, arr[0].length, arr);

        if (answer[0] == 4 && answer[1] == 0)
            answer[0] = 1;
        else if (answer[0] == 0 && answer[1] == 4)
            answer[1] = 1;

        return answer;
    }

    static void solve(int y, int x, int d, int[][] arr) {
        if (d == 1)
            return;
        // 범위 4분할
        int nd = d / 2;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i] * nd;
            int nx = x + dx[i] * nd;

            // 선택된 범위가 모두 같은 번호이면 더 내려갈 필요가 없음
            int now = arr[ny][nx];
            if (check(now, ny, nx, nd, arr)) {
                answer[now]++;
            }
            // 하나라도 다르면 내려감
            else {
                solve(ny, nx, nd, arr);
            }
        }
    }
    // 선택된 범위에 번호가 모두 같은지 확인하는 함수
    static boolean check(int now, int ny, int nx, int d, int[][] arr) {
        for (int y = ny; y < ny + d; y++) {
            for (int x = nx; x < nx + d; x++) {
                if (now != arr[y][x])
                    return false;
            }
        }
        return true;
    }
}