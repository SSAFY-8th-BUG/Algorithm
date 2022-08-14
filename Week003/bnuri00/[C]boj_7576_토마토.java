package week003;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class C_boj_7576_토마토 {
	static int M, N, day, tomato0;
	static int[][] map;
	static Queue<Point> q = new ArrayDeque<>();
	static boolean[][] visited;
	

	// 상 하 좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 입력, 초기상태에서 익어있는 토마토 큐에 넣기
		map = new int[N][M];
		visited = new boolean[N][M];
		boolean allRip = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) tomato0++;	//안익은 토마토 세기
				if (map[i][j] == 1) {
					q.offer(new Point(j, i));
					visited[i][j] = true;
				}
			}
		}
		
		// dfs 및 출력
		// 예외상황 체크
		
		// 초기 토마토 모두 안 익은 경우
		if(q.isEmpty()) System.out.println("-1");
		else if(tomato0 == 0) System.out.println("0");	// 안익은 토마토 없음 = 모두 익음
		else {	// 일반적인 경우
			bfs();
			for(int m[]:map) {
				for(int i : m) {
					if(i == 0) allRip = false;
				}
			}
			if(!allRip) System.out.println("-1");
			else System.out.println(day-1);
		}
		

	}

	static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			day++;

			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				int x = p.x;
				int y = p.y;

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || ny < 0 || nx >= M || ny >= N)
						continue;

					visited[ny][nx] = true;
					if (map[ny][nx] != 0)
						continue;

					map[ny][nx] = 1;
					q.add(new Point(nx, ny));

				}
			}
		}
		

	}

}
