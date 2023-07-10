
// 시간초과
// DP 스럽게 생각해봐야함
class Solution {
    static int MOD = 20170805;
    static int answer;
    static boolean[][] v;
    static int[] dy = {0, -1};
    static int[] dx = {-1, 0};
    static int d;

    public int solution(int m, int n, int[][] cityMap) {
        answer = 0;

        v = new boolean[m][n];
        v[m - 1][n - 1] = true;
        dfs(m - 1, n - 1, 0, m, n, cityMap);

        return answer;
    }

    static void dfs(int y, int x, int d, int m, int n, int[][] cityMap) {
        if (y == 0 && x == 0) {
            answer++;
            answer %= MOD;
            return;
        }

        for (int i = 0; i < 2; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (cityMap[y][x] == 2 && i != d) continue;
            if (ny < 0 || ny >= m || nx < 0 || nx >= n || v[ny][nx] || cityMap[ny][nx] == 1) continue;

            v[ny][nx] = true;
            dfs(ny, nx, i, m, n, cityMap);
            v[ny][nx] = false;
        }
    }
}