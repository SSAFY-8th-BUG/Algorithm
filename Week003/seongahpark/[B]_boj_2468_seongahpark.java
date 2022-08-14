import java.util.*;
import java.io.*;

public class Main {

	public static int n, h, res;
	public static int [][] arr;
	public static boolean [][] visited;
	
	static int [] dx = {0, 0, 1, -1};
	static int [] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
				
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				h = Math.max(h, arr[i][j]);
			}
		}
		
		// 높이 별로 탐색
		for(int z=0; z<=h; z++) {
			visited = new boolean[n][n];
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visited[i][j] && arr[i][j] > z) cnt += dfs(i, j, z);
				}
			}
			res = Math.max(res, cnt);
		}
		
		System.out.println(res);
	}

	// 안전 영역 dfs로 탐색
	static int dfs(int x, int y, int h) {
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
			if(arr[nx][ny] > h) dfs(nx, ny, h);
		}
		
		return 1;
	}
}
