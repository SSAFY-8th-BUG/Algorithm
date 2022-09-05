package week006;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 1시간 40분
 * 참고: X
 * 
 * 16432KB		180ms
 * 
 * <풀이방식>
 * - dfs
 * 
 * - 상자와 닿은 치즈부분도 녹음
 * - 검사할 상자를 줄여가며 구한다. (상자만큼만 bfs의 visit배열 초기화)
 * - dfs로 공기부분 탐색하며 치즈 녹인다.
 * - 치즈부분은 큐에 넣지 않음
 * 
 * 
 * <실수한거>
 * - nx 쓸 자리에 ny썼음;
 * 
 * */
public class Dboj_2636_bnuri00 {
	static int N, M; // 세로 가로
	static int[][] map;

	static Queue<Point> q = new ArrayDeque<>();

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 배열 입력받기
		int cheeseNum = 0;	// 치즈 수
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == 1) cheeseNum++;
			}
		}

		// 상자 크기 (왼쪽위, 오른쪽아래)
		int sy = 0; 
		int sx = 0;
		int ey = N-1;
		int ex = M-1;
		
		// 시간 재기
		int time = 0;
		
		while (true) {
			time++;
			
			int melt = bfs(sx, sy);

			if (cheeseNum - melt == 0) break;	// 다 녹은 경우  while문 탈출 (녹은 치즈는 빼지 않아야 다 녹기 바로 전 치즈갯수 나옴)
			else cheeseNum -= melt;		// 전체 치즈에서 녹은 치즈 빼주기
			
			// 상자 줄이기
			sy = ++sx;
			ey--;
			ex--;

			// 상자만큼만 visit 배열 초기화
			q.clear();
			for (int i = sy; i <= ey; i++) {
				for (int j = sx; j <= ex; j++) {
					visit[i][j] = false;
				}
			}
		}

		System.out.print(time + "\n" + cheeseNum);

	}

	/**
	 * bfs로 빈 공간 탐색함, 탐색 중 치즈를 만나면 큐에 넣지 않고 녹인다.
	 * 
	 * @param 상자 왼쪽 끝 좌표 (x, y)
	 * @return 녹은 치즈 개수
	 * */
	static int bfs(int x, int y) {
		q.add(new Point(x, y));
		visit[y][x] = true;

		int melt = 0;
		int tmp = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			

			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];

				// 범위 벗어남 or 이미 방문
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx])	
					continue;

				if (map[ny][nx] == 1) {
					map[ny][nx] = 0;
					melt++;

					visit[ny][nx] = true;
					
					// 큐에 들어가면 치즈가 다 녹아버리므로 큐에 넣지 않고 넘어간다
					continue;
				}
				
				// 공기중일 경우 큐에 넣음
				q.add(new Point(nx, ny));
				visit[ny][nx] = true;
				
			}	
		}
		return melt;
	}

}
