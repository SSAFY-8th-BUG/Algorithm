import java.util.*;
import java.io.*;

public class Main {

    static int N, M, startX, startY;
    static char[][] map;
    static boolean[][] checked;

    static char dir;
    static String ans;

                     // 하, 상, 우, 좌
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, - 1};

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        checked = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        outer: for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#' && isStartPoint(i, j)) {
                    checked[i][j] = true;
                    startX = i;
                    startY = j;
                    dfs(i, j, dir);
                    break outer;
                }
            }
        }

        ans = sb.toString();

        System.out.println((startX + 1) + " " + (startY + 1));
        System.out.println(dir);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int direction) {
        if (direction == '>') {
            if (y + 2 < M && !checked[x][y + 1] && !checked[x][y + 2] && map[x][y + 1] == '#' && map[x][y + 2] == '#') {
                checked[x][y + 1] = true;
                checked[x][y + 2] = true;
                sb.append("A");
                dfs(x, y + 2, '>');
            } else {
                // > --> v
                if (x + 1 < N && !checked[x + 1][y] && map[x + 1][y] == '#') {
                    sb.append("R");
                    dfs(x, y, 'v');
                } else if (x - 1 >= 0 && !checked[x - 1][y] && map[x - 1][y] == '#') {
                    // > --> ^
                    sb.append("L");
                    dfs(x, y, '^');
                }
            }
        } else if (direction == '<') {
            if (y - 2 >= 0 && !checked[x][y - 1] && !checked[x][y - 2] && map[x][y - 1] == '#' && map[x][y - 2] == '#') {
                checked[x][y - 1] = true;
                checked[x][y - 2] = true;
                sb.append("A");
                dfs(x, y - 2, '<');
            } else {
                // < --> v
                if (x + 1 < N && !checked[x + 1][y] && map[x + 1][y] == '#') {
                    sb.append("L");
                    dfs(x, y, 'v');
                } else if (x - 1 >= 0 && !checked[x - 1][y] && map[x - 1][y] == '#') {
                    // < --> ^
                    sb.append("R");
                    dfs(x, y, '^');
                }
            }
        } else if (direction == '^') {
            if (x - 2 >= 0 && !checked[x - 1][y] && !checked[x - 2][y] && map[x - 1][y] == '#' && map[x - 2][y] == '#') {
                checked[x - 1][y] = true;
                checked[x - 2][y] = true;
                sb.append("A");
                dfs(x - 2, y, '^');
            } else {
                // ^ -> >
                if (y + 1 < M && !checked[x][y + 1] && map[x][y + 1] == '#') {
                    sb.append("R");
                    dfs(x, y, '>');
                } else if (y - 1 >= 0 && !checked[x][y - 1] && map[x][y - 1] == '#') {
                    // ^ -> <
                    sb.append("L");
                    dfs(x, y, '<');
                }
            }
        } else if (direction == 'v') {
            if (x + 2 < N && !checked[x + 1][y] && !checked[x + 2][y] && map[x + 1][y] == '#' && map[x + 2][y] == '#') {
                checked[x + 1][y] = true;
                checked[x + 2][y] = true;
                sb.append("A");
                dfs(x + 2, y, 'v');
            } else {
                // v -> >
                if (y + 1 < M && !checked[x][y + 1] && map[x][y + 1] == '#') {
                    sb.append("L");
                    dfs(x, y, '>');
                } else if (y - 1 >= 0 && !checked[x][y - 1] && map[x][y - 1] == '#') {
                    // v -> <
                    sb.append("R");
                    dfs(x, y, '<');
                }
            }
        }
    }

    static boolean isStartPoint(int x, int y) {
        int count = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == '#') {
                count++;

                if (d == 0) dir = 'v';
                else if (d == 1) dir = '^';
                else if (d == 2) dir = '>';
                else dir = '<';
            }
        }

        return count == 1 ? true : false;
    }
}