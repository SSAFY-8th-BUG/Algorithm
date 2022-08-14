package study;
import java.util.*;
import java.io.*;

public class Main {

	public static int n, m, ans = -1;
	public static int [][] arr, res;
	public static boolean [][] visited;
	
	static int [] dx = {0, 0, 1, -1};
	static int [] dy = {1, -1, 0, 0}; 
	
	static Queue<int []> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		res = new int[n][m];
		visited = new boolean[n][m];
				
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) q.add(new int[] {i, j});
				// 익은 토마토는 queue 삽입
			}
		}
		
		bfs();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				// 익지 않은게 있으면 바로 출력
				if(arr[i][j] == 0 && res[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				
				ans = Math.max(ans, res[i][j]);
			}
		}
			
		System.out.println(ans);
	}

    // bfs로 인근 탐색
	static void bfs() {
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				if(arr[ny][nx] == 0 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
					res[ny][nx] = res[y][x] + 1;
				}
			}
		}
	}
}
