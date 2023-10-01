class Solution {
    static boolean[][] gi;
    static boolean[][] bo;

    public int[][] solution(int n, int[][] build_frame) {
        gi = new boolean[n + 1][n + 1];
        bo = new boolean[n + 1][n + 1];
        int count = 0;

        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int a = bf[2];
            int b = bf[3];

            // 기둥
            if (a == 0) {
                // 설치
                if (b == 1) {
                    if (checkGi(x, y)) {
                        gi[x][y] = true;
                        count++;
                    }
                }
                // 삭제
                else {
                    gi[x][y] = false;
                    if (canDelete(n)) {
                        count--;
                    } else {
                        gi[x][y] = true;
                    }
                }
            }
            // 보
            else {
                // 설치
                if (b == 1) {
                    if (checkBo(x, y)) {
                        bo[x][y] = true;
                        count++;
                    }
                }
                // 삭제
                else {
                    bo[x][y] = false;
                    if (canDelete(n)) {
                        count--;
                    } else {
                        bo[x][y] = true;
                    }
                }
            }
        }

        int[][] answer = new int[count][3];
        int idx = 0;
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (gi[x][y]) {
                    answer[idx][0] = x;
                    answer[idx][1] = y;
                    answer[idx][2] = 0;
                    idx++;
                }
                if (bo[x][y]) {
                    answer[idx][0] = x;
                    answer[idx][1] = y;
                    answer[idx][2] = 1;
                    idx++;
                }
            }
        }

        return answer;
    }

    static boolean canDelete(int n) {
        for (int y = 0; y <= n; y++) {
            for (int x = 0; x <= n; x++) {
                if (gi[x][y] && !checkGi(x, y)) {
                    return false;
                } else if (bo[x][y] && !checkBo(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkGi(int x, int y) {
        if (y == 0) return true;
        else if (y > 0 && gi[x][y - 1]) return true;
        else if (x > 0 && bo[x - 1][y] || bo[x][y]) return true;
        return false;
    }

    static boolean checkBo(int x, int y) {
        if (y > 0 && gi[x][y - 1] || gi[x + 1][y - 1]) return true;
        else if (x > 0 && bo[x - 1][y] && bo[x + 1][y]) return true;
        return false;
    }
}