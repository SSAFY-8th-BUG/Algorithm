import java.util.*;
import java.io.*;

class swea_1953_tableMinPark {
    static int T, N, M, R, C, L, answer;
	static int[][] map;
    // 1, 2, 3, 4, 5, 6, 7
	static int[][] dy = {{0}, {1, -1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
	static int[][] dx = {{0}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
	
	public static void main (String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			bfs(R, C);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
    static class P {
		int y;
        int x;
        int d;
        public P(int y, int x, int d){
        	this.y = y;
            this.x = x;
            this.d = d;
        }
    }
    
    static boolean check (int y, int x, int nextY, int nextX){
    	int pipe = map[nextY][nextX];
        
        for (int i = 0; i < dy[pipe].length; i++) {
        	int ny = nextY + dy[pipe][i];
            int nx = nextX + dx[pipe][i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            
            if (ny == y && nx == x) return true;
        }
        return false;
    }
    static void bfs(int y, int x){
    	Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        
        q.offer(new P(y, x, 1));
        v[y][x] = true;
        
        while(!q.isEmpty()) {
        	P now = q.poll();
            int pipe = map[now.y][now.x];
                        
            answer++;
            if (now.d == L) continue;
            
            
            for (int i = 0; i < dy[pipe].length; i++) {
             	int ny = now.y + dy[pipe][i];
                int nx = now.x + dx[pipe][i];
                
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx]) continue;                
                if (map[ny][nx] == 0) continue;
                if (!check(now.y, now.x, ny, nx)) continue;
                
                q.offer(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }    
    }
}