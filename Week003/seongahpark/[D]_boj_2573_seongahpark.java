package study;
import java.util.*;
import java.io.*;

public class Main {

	public static int n, m, res;
	public static int [][] arr;
	public static boolean [][] visited;
	
	static int [] dx = {0, 0, 1, -1};
	static int [] dy = {1, -1, 0, 0}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
				
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int part = 1;
		while(true) {
			part = melt();
			if(part > 1) break;
			else if(part == -1) {
				res = 0;
				break;
			}
			chk();
			res++;
		}
		
		System.out.println(res);
	}
	
	static int melt() {
		visited = new boolean[n][m];
		int part = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!visited[i][j] && arr[i][j] > 0) {
					part++;
					if(part > 1) return part;
					bfs(i, j);
				}
			}
		}
		if(part == 0) return -1;
		return part;
	}
	
	static void bfs(int x, int y) {
		Queue<int []> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int cx = q.peek()[0];
			int cy = q.peek()[1];
			q.poll();
			
			int zero = 0;
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if( nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
				if(arr[nx][ny] > 0) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				if(arr[nx][ny] == 0) zero++;
			}
			
			arr[cx][cy] = arr[cx][cy] - zero > 0 ? arr[cx][cy] - zero : -1;
		}
	}
	
	// melt 함수 후 녹아서 0이 된 빙산은 다른 빙산을 살펴볼 때 0으로 카운트 되면 안된다
	// 따라서 -1로 먼저 할당해주고 chk 함수로 다시 -1을 0으로 바꿔줌
	// 다른 방법으로는 배열을 카피하여서 따로 체크하는 것도 가능
	static void chk() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == -1) arr[i][j] = 0;
			}
		}
	}
}
