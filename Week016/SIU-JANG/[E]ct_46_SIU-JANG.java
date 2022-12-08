package ct;

import java.util.Scanner;
import java.util.ArrayList;

public class ct_꼬리잡기놀이_46 {
    public static final int DIR_NUM = 4;
    public static final int MAX_M = 5;
    public static final int MAX_N = 20;
    
    public static int n, m, k;
    public static int[][] board = new int[MAX_N + 1][MAX_N + 1];
    
    // 각 팀별 레일 위치를 관리합니다.
    public static ArrayList<Pair>[] v = new ArrayList[MAX_M + 1];
    // 각 팀별 tail 위치를 관리합니다.
    public static int[] tail = new int[MAX_M + 1];
    public static boolean[][] visited = new boolean[MAX_N + 1][MAX_N + 1];
    
    // 격자 내 레일에 각 팀 번호를 적어줍니다.
    public static int[][] boardIdx = new int[MAX_N + 1][MAX_N + 1];
    
    public static int ans;
    
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    
    public static boolean isOutRange(int x, int y) {
        return !(1 <= x && x <= n && 1 <= y && y <= n);
    }
    
    // 초기 레일을 만들기 위해 dfs를 이용합니다.
    public static void dfs(int x, int y, int idx) {
        visited[x][y] = true;
        boardIdx[x][y] = idx;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isOutRange(nx, ny)) continue;
    
            // 이미 지나간 경로거나 경로가 아니면 넘어갑니다.
            if(board[nx][ny] == 0) continue;
            if(visited[nx][ny]) continue;
    
            // 가장 처음 탐색할 때 2가 있는 방향으로 dfs를 진행합니다.
            if(v[idx].size() == 1 && board[nx][ny] != 2) continue;
    
            v[idx].add(new Pair(nx, ny));
            if(board[nx][ny] == 3) tail[idx] = v[idx].size();
            dfs(nx, ny, idx);
        }
    }
    
    // 입력을 받는 등 초기 작업을 합니다.
    public static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                board[i][j] = sc.nextInt();
    
        for(int i = 1; i <= m; i++)
            v[i] = new ArrayList<>();

        int cnt = 1;
    
        // 레일을 벡터에 저장합니다. 머리사람을 우선 앞에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                if(board[i][j] == 1) v[cnt++].add(new Pair(i, j));
    
        // dfs를 통해 레일을 벡터에 순서대로 넣어줍니다.
        for(int i = 1; i <= m; i++)
            dfs(v[i].get(0).x, v[i].get(0).y, i);
    }
    
    // 각 팀을 이동시키는 함수입니다.
    public static void moveAll() {
        for(int i = 1; i <= m; i++) {
            // 각 팀에 대해 레일을 한 칸씩 뒤로 이동시킵니다.
            Pair tmp = v[i].get(v[i].size() - 1);
            for(int j = v[i].size() - 1; j >= 1; j--)
                v[i].set(j, v[i].get(j - 1));
            v[i].set(0, tmp);
        }
    
        for(int i = 1; i <= m; i++) {
            // 벡터에 저장한 정보를 바탕으로 보드의 표기 역시 바꿔줍니다.
            for(int j = 0; j < v[i].size(); j++) {
                Pair pos = v[i].get(j);
                if(j == 0)
                    board[pos.x][pos.y] = 1;
                else if(j < tail[i] - 1)
                    board[pos.x][pos.y] = 2;
                else if(j == tail[i] - 1)
                    board[pos.x][pos.y] = 3;
                else
                    board[pos.x][pos.y] = 4;
            }
        }
    }
    
    // (x, y) 지점에 공이 닿았을 때의 점수를 계산합니다.
    public static void getPoint(int x, int y) {
        int idx = boardIdx[x][y];
        int cnt = 0;
        for(int i = 0; i < v[idx].size(); i++)
            if(v[idx].get(i).x == x && v[idx].get(i).y == y) 
                cnt = i;

        ans += (cnt + 1) * (cnt + 1);
    }
    
    // turn 번째 라운드의 공을 던집니다.
    // 공을 던졌을 때 이를 받은 팀의 번호를 반환합니다.
    public static int throwBall(int turn) {
        int t = (turn - 1) % (4 * n) + 1;
    
        if(t <= n) {
            // 1 ~ n 라운드의 경우 왼쪽에서 오른쪽 방향으로 공을 던집니다.
            for(int i = 1; i <= n; i++) {
                if(1 <= board[t][i] && board[t][i] <= 3) {
                    // 사람이 있는 첫 번째 지점을 찾습니다.
                    // 찾게 되면 점수를 체크한 뒤 찾은 사람의 팀 번호를 저장합니다.
                    getPoint(t, i);
                    return boardIdx[t][i];
                }
            }
        }
        else if(t <= 2 * n) {
            // n+1 ~ 2n 라운드의 경우 아래에서 윗쪽 방향으로 공을 던집니다.
            t -= n;
            for(int i = 1; i <= n; i++) {
                if(1 <= board[n + 1 - i][t] && board[n + 1 - i][t] <= 3) {
                    // 사람이 있는 첫 번째 지점을 찾습니다.
                    // 찾게 되면 점수를 체크한 뒤 찾은 사람의 팀 번호를 저장합니다.
                    getPoint(n + 1 - i, t);
                    return boardIdx[n + 1 - i][t];
                }
            }
        }
        else if(t <= 3 * n) {
            // 2n+1 ~ 3n 라운드의 경우 오른쪽에서 왼쪽 방향으로 공을 던집니다.
            t -= (2 * n);
            for(int i = 1; i <= n; i++) {
                if(1 <= board[n + 1 - t][n + 1 - i] && board[n + 1 - t][n + 1 - i] <= 3) {
                    // 사람이 있는 첫 번째 지점을 찾습니다.
                    // 찾게 되면 점수를 체크한 뒤 찾은 사람의 팀 번호를 저장합니다.
                    getPoint(n + 1 - t, n + 1 - i);
                    return boardIdx[n + 1 - t][n + 1 - i];
                }
            }
        }
        else {
            // 3n+1 ~ 4n 라운드의 경우 위에서 아랫쪽 방향으로 공을 던집니다.
            t -= (3 * n);
            for(int i = 1; i <= n; i++) {
                if(1 <= board[i][n + 1 - t] && board[i][n + 1 - t] <= 3) {
                    // 사람이 있는 첫 번째 지점을 찾습니다.
                    // 찾게 되면 점수를 체크한 뒤 찾은 사람의 팀 번호를 저장합니다.
                    getPoint(i, n + 1 - t);
                    return boardIdx[i][n + 1 - t];
                }
            }
        }
        // 공이 그대로 지나간다면 0을 반환합니다.
        return 0;
    }
    
    // 공을 획득한 팀을 순서를 바꿉니다.
    public static void reverse(int gotBallIdx) {
        // 아무도 공을 받지 못했으면 넘어갑니다.
        if(gotBallIdx == 0) return;
    
        int idx = gotBallIdx;
    
        ArrayList<Pair> newV = new ArrayList<>();
    
        // 순서를 맞춰 newV에 레일을 넣어줍니다.
        for(int i = tail[idx] - 1; i >= 0; i--) {
            Pair pos = v[idx].get(i);
            newV.add(pos);
        }
    
        for(int i = v[idx].size() - 1; i >= tail[idx]; i--) {
            Pair pos = v[idx].get(i);
            newV.add(pos);
        }

        // newV값을 다시 v[idx]에 옮겨줍니다.
        for(int i = 0; i < v[idx].size(); i++)
            v[idx].set(i, newV.get(i));
    
        // 벡터에 저장한 정보를 바탕으로 보드의 표기 역시 바꿔줍니다.
        for(int j = 0; j < v[idx].size(); j++) {
            Pair pos = v[idx].get(j);
            if(j == 0)
                board[pos.x][pos.y] = 1;
            else if(j < tail[idx] - 1)
                board[pos.x][pos.y] = 2;
            else if(j == tail[idx] - 1)
                board[pos.x][pos.y] = 3;
            else
                board[pos.x][pos.y] = 4;
        }
    }

    public static void main(String[] args) {
        // 입력을 받고 구현을 위한 기본적인 처리를 합니다.
        init();

        for(int i = 1; i <= k; i++) {
            // 각 팀을 머리사람을 따라 한칸씩 이동시킵니다.
            moveAll();

            // i번째 라운드의 공을 던집니다. 공을 받은 사람을 찾아 점수를 추가합니다.
            int gotBallIdx = throwBall(i);

            // 공을 획득한 팀의 방향을 바꿉니다.
            reverse(gotBallIdx);
        }

        System.out.print(ans);
    }
    
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}