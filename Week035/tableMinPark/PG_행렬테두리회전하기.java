class 행렬테두리회전하기 {
    static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int num = 1;
        map = new int[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                map[y][x] = num++;
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int sy = q[0] - 1;
            int sx = q[1] - 1;
            int ey = q[2] - 1;
            int ex = q[3] - 1;
            int n = rotate(sy, sx, ey, ex);
            answer[i] = n;
        }

        return answer;
    }

    static int rotate(int sy, int sx, int ey, int ex) {
        int n = map[sy][sx];
        int nn = map[sy][sx];

        // 상
        int ny = sy;
        int nx = sx + 1;
        for (int x = nx; x <= ex; x++) {
            int temp = map[ny][x];
            map[ny][x] = nn;
            nn = temp;
            n = Math.min(n, nn);
        }

        // 우
        ny = sy + 1;
        nx = ex;
        for (int y = ny; y <= ey; y++) {
            int temp = map[y][nx];
            map[y][nx] = nn;
            nn = temp;
            n = Math.min(n, nn);
        }

        // 하
        ny = ey;
        nx = ex - 1;
        for (int x = nx; x >= sx; x--) {
            int temp = map[ny][x];
            map[ny][x] = nn;
            nn = temp;
            n = Math.min(n, nn);
        }

        // 좌
        ny = ey - 1;
        nx = sx;
        for (int y = ny; y >= sy; y--) {
            int temp = map[y][nx];
            map[y][nx] = nn;
            nn = temp;
            n = Math.min(n, nn);
        }

        return n;
    }
}