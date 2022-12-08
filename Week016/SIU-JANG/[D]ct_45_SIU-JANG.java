package ct;

import java.util.Scanner;

public class ct_예술성_45 {
	public static final int DIR_NUM = 4;
    public static final int MAX_N = 29;
    
    // 변수 선언
    public static int n;
    public static int[][] arr = new int[MAX_N][MAX_N];
    public static int[][] nextArr = new int[MAX_N][MAX_N];
    
    // 그룹의 개수를 관리합니다.
    public static int groupN;
    
    // 각 칸에 그룹 번호를 적어줍니다.
    public static int[][] group = new int[MAX_N][MAX_N];
    public static int[] groupCnt = new int[MAX_N * MAX_N + 1]; // 각 그룹마다 칸의 수를 세줍니다.
    
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    
    public static int[] dx = new int[]{1, -1, 0,  0};
    public static int[] dy = new int[]{0,  0, 1, -1};
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    // (x, y) 위치에서 DFS를 진행합니다.
    public static void dfs(int x, int y) {
        for(int i = 0; i < DIR_NUM; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            // 인접한 칸 중 숫자가 동일하면서 방문한 적이 없는 칸으로만 이동이 가능합니다.
            if(inRange(nx, ny) && !visited[nx][ny] && arr[nx][ny] == arr[x][y]){
                visited[nx][ny] = true;
                group[nx][ny] = groupN;
                groupCnt[groupN]++;
                dfs(nx, ny);
            }
        }
    }
    
    // 그룹을 만들어줍니다.
    public static void makeGroup() {
        groupN = 0;
    
        // visited 값을 초기화 해줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                visited[i][j] = false;
    
        // DFS를 이용하여 그룹 묶는 작업을 진행합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    groupN++;
                    visited[i][j] = true;
                    group[i][j] = groupN;
                    groupCnt[groupN] = 1;
                    dfs(i, j); 
                }
            }
    }
    
    public static int getArtScore() {
        int artScore = 0;
        
        // 특정 변을 사이에 두고
        // 두 칸의 그룹이 다른 경우라면
        // (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수) x 그룹 a를 이루고 있는 숫자 값 x 그룹 b를 이루고 있는 숫자 값
        // 만큼 예술 점수가 더해집니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 0; k < DIR_NUM; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if(inRange(nx, ny) && arr[i][j] != arr[nx][ny]) {
                        int g1 = group[i][j], g2 = group[nx][ny];
                        int num1 = arr[i][j], num2 = arr[nx][ny];
                        int cnt1 = groupCnt[g1], cnt2 = groupCnt[g2];
                        
                        artScore += (cnt1 + cnt2) * num1 * num2;
                    }
                }
        
        // 중복 계산을 제외해줍니다.
        return artScore / 2;
    }
    
    public static int getScore() {
        // Step 1. 그룹을 형성합니다.
        makeGroup();
    
        // Step 2. 예술 점수를 계산해줍니다.
        return getArtScore();
    }
    
    public static void rotateSquare(int sx, int sy, int squareN) {
        // 정사각형을 시계방향으로 90' 회전합니다.
        for(int x = sx; x < sx + squareN; x++)
            for(int y = sy; y < sy + squareN; y++) {
                // Step 1. (sx, sy)를 (0, 0)으로 옮겨주는 변환을 진행합니다. 
                int ox = x - sx, oy = y - sy;
                // Step 2. 변환된 상태에서는 회전 이후의 좌표가 (x, y) -> (y, squareN - x - 1)가 됩니다.
                int rx = oy, ry = squareN - ox - 1;
                // Step 3. 다시 (sx, sy)를 더해줍니다.
                nextArr[rx + sx][ry + sy] = arr[x][y];
            }
    }
    
    public static void rotate() {
        // Step 1. next arr값을 초기화해줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextArr[i][j] = 0;
        
        // Step 2. 회전을 진행합니다.
        
        // Step 2 - 1. 십자 모양에 대한 반시계 회전을 진행합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                // Case 2 - 1. 세로줄에 대해서는 (i, j) -> (j, i)가 됩니다.
                if(j == n / 2)
                    nextArr[j][i] = arr[i][j];
                // Case 2 - 2. 가로줄에 대해서는 (i, j) -> (n - j - 1, i)가 됩니다.
                else if(i == n / 2)
                    nextArr[n - j - 1][i] = arr[i][j];
            }
    
        // Step 2 - 2. 4개의 정사각형에 대해 시계 방향 회전을 진행합니다.
        int sqaureN = n / 2;
        rotateSquare(0, 0, sqaureN);
        rotateSquare(0, sqaureN + 1, sqaureN);
        rotateSquare(sqaureN + 1, 0, sqaureN);
        rotateSquare(sqaureN + 1, sqaureN + 1, sqaureN);
        
        // Step 3. next arr값을 다시 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                arr[i][j] = nextArr[i][j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();

        // 3회전까지의 예술 점수를 더해줍니다.
        int ans = 0; 
        for(int i = 0; i < 4; i++) {
            // 현재 예술 점수를 더해줍니다.
            ans += getScore();

            // 회전을 진행합니다.
            rotate();
        }

        System.out.print(ans);
    }
}
