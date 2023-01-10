import java.io.*;
import java.util.*;

public class bj_22116_tableMinPark {

    static int N, max, min;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        max = -1;
        min = Integer.MAX_VALUE;
        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken()); 
                min = Math.min(min, map[y][x]);
                max = Math.max(max, map[y][x]);               
            }
        }

        int left = 0;
        int right = max - min;
        while(left <= right) {
            int mid = (left + right) / 2;
 
            if(bfs(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);  
        br.close();
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static class P {
        int y;
        int x;
        public P(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static boolean bfs(int mid) {
        Queue<P> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
 
        q.offer(new P(0, 0));
        visited[0][0] = true;
 
        while(!q.isEmpty()) {
            P now = q.poll();
 
            if(now.x == N - 1 && now.y == N - 1) return true;
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
 
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny] || Math.abs(map[nx][ny] - map[now.x][now.y]) > mid) continue;
                visited[nx][ny] = true;
                q.offer(new P(ny, nx));
            }
        }
        return false;
    }
    
}