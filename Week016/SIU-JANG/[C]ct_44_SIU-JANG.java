package ct;

import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Pair p) {
        return this.x == p.x && this.y == p.y;
    }
}

public class ct_술래잡기_44 {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    public static int n, m, h, k;
    public static ArrayList<Integer>[][] hiders = new ArrayList[MAX_N][MAX_N];
    public static ArrayList<Integer>[][] nextHiders = new ArrayList[MAX_N][MAX_N];
    public static boolean[][] tree = new boolean[MAX_N][MAX_N];

    public static int[][] seekerNextDir = new int[MAX_N][MAX_N];
    public static int[][] seekerRevDir = new int[MAX_N][MAX_N];
    
    public static Pair seekerPos;
    public static boolean forwardFacing = true;
    
    public static int ans;
    
    public static void initializeSeekerPath() {
        int[] dx = new int[]{-1, 0, 1,  0};
        int[] dy = new int[]{0 , 1, 0, -1};
    
        int currX = n / 2, currY = n / 2;
        int moveDir = 0, moveNum = 1;
    
        while(currX > 0 || currY > 0) {
            for(int i = 0; i < moveNum; i++) {
                seekerNextDir[currX][currY] = moveDir;
                currX += dx[moveDir]; currY += dy[moveDir];
                seekerRevDir[currX][currY] = (moveDir < 2) ? (moveDir + 2) : (moveDir - 2);
    
                if(currX == 0 && currY == 0)
                    break;
            }
            
            moveDir = (moveDir + 1) % 4;
            if(moveDir == 0 || moveDir == 2)
                moveNum++;
        }
    }
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static void hiderMove(int x, int y, int moveDir) {
        int[] dx = new int[]{0 , 0, 1, -1};
        int[] dy = new int[]{-1, 1, 0,  0};
    
        int nx = x + dx[moveDir], ny = y + dy[moveDir];
        if(!inRange(nx, ny)) {
            moveDir = (moveDir < 2) ? (1 - moveDir) : (5 - moveDir);
            nx = x + dx[moveDir]; ny = y + dy[moveDir];
        }
        
        if(!new Pair(nx, ny).isSame(seekerPos))
            nextHiders[nx][ny].add(moveDir);
        else
            nextHiders[x][y].add(moveDir);
    }
    
    public static int distFromSeeker(int x, int y) {
        int seekerX = seekerPos.x;
        int seekerY = seekerPos.y;
    
        return Math.abs(seekerX - x) + Math.abs(seekerY - y);
    }
    
    public static void hiderMoveAll() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextHiders[i][j] = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                if(distFromSeeker(i, j) <= 3) {
                    for(int k = 0; k < hiders[i][j].size(); k++)
                        hiderMove(i, j, hiders[i][j].get(k));
                }
                else {
                    for(int k = 0; k < hiders[i][j].size(); k++)
                        nextHiders[i][j].add(hiders[i][j].get(k));
                }
            }
    
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                hiders[i][j] = new ArrayList<>(nextHiders[i][j]);
    }
    
    public static int getSeekerDir() {
        int x = seekerPos.x;
        int y = seekerPos.y;
    
        int moveDir;
        if(forwardFacing)
            moveDir = seekerNextDir[x][y];
        else
            moveDir = seekerRevDir[x][y];
        
        return moveDir;
    }
    
    public static void checkFacing() {
        if(seekerPos.isSame(new Pair(0, 0)) && forwardFacing)
            forwardFacing = false;
        if(seekerPos.isSame(new Pair(n / 2, n / 2)) && !forwardFacing)
            forwardFacing = true;
    }
    
    public static void seekerMove() {
        int x = seekerPos.x;
        int y = seekerPos.y;
    
        int[] dx = new int[]{-1, 0, 1,  0};
        int[] dy = new int[]{0 , 1, 0, -1};
    
        int moveDir = getSeekerDir();
    
        seekerPos = new Pair(x + dx[moveDir], y + dy[moveDir]);
        
        checkFacing();
    }
    
    public static void getScore(int t) {
        int[] dx = new int[]{-1, 0, 1,  0};
        int[] dy = new int[]{0 , 1, 0, -1};
    
        int x = seekerPos.x;
        int y = seekerPos.y;
        
        int moveDir = getSeekerDir();
        
        for(int dist = 0; dist < 3; dist++) {
            int nx = x + dist * dx[moveDir], ny = y + dist * dy[moveDir];
            
            if(inRange(nx, ny) && !tree[nx][ny]) {
                ans += t * hiders[nx][ny].size();
    
                hiders[nx][ny] = new ArrayList<>();
            }
        }
    }
    
    public static void simulate(int t) {
        hiderMoveAll();
    
        seekerMove();
        
        getScore(t);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                hiders[i][j] = new ArrayList<>();

        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            hiders[x - 1][y - 1].add(d);
        }

        while(h-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            tree[x - 1][y - 1] = true;
        }

        seekerPos = new Pair(n / 2, n / 2);

        initializeSeekerPath();

        for(int t = 1; t <= k; t++)
            simulate(t);
        
        System.out.print(ans);
    }
}