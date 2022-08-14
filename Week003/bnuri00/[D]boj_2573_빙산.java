package week003;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class D_boj_2573_빙산 {
	
	static int N, M, year;
	static int[][] map;
	static boolean[][] visit;
	
	// 상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean isSeparate = false;
		while(true) {
			visit = new boolean[N][M];
			
			int iceCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0||visit[i][j]) continue;
					
					bfs(j, i);
					iceCnt++;
				}
			}
			year++;
			
			if (iceCnt==0) break;	// 빙산 다녹아버림..(다녹을때까지 분리X)	
			else if(iceCnt>1) {		// 빙산 2개 이상임 (빙산 분리O)
				isSeparate = true;
				break;
			}
		}	
		
		if(!isSeparate) year = 0;
		else year--;	// 현재년도에서 1 빼주는 이유는 전년도 빙산이 검사되기 때문
		
		System.out.println(year);
	}
	static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		
		q.add(new Point(x, y));
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			x = p.x;
			y = p.y;
			System.out.println("x: "+x + " y: "+y);
			
			// 4방향 탐색
			for(int d = 0; d < 4; d++) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				
				// 범위 벗어남 or 이미 방문
				if(nx < 0 || ny < 0|| nx >= M || ny >= N||visit[ny][nx])	continue;

				
				// 탐색한 곳이 0인 경우  직전 큐에서 꺼내진 p 좌표의 빙산 녹음!!! 
				//
				// : 이전에 탐색하고 0으로 바뀐 부분은 위의 if에서 걸러짐)
				// : 다음 탐색에서도 볼 수 있어야 하므로 방문표시 X, 큐에 넣지 않고 넘김
				if(map[ny][nx]==0) {
					map[y][x] = Math.max(0, map[y][x]-1);	// 0보다 작은 경우 더이상 값 줄이지 않음(빙산 다녹음)
					continue;
				}
				
				// 방문한 위치 큐에 넣기, 방문 표시
				q.add(new Point(nx, ny));
				visit[ny][nx] = true;
			}
			
		}
	}

}
