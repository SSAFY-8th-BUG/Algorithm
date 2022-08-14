package week003;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 메모리: 36444 KB
 * 시간: 304 ms
 * */
public class B_boj_2468_안전영역 {

	static int N, safeArea = 1;
	static int[][] map;
	static boolean[][] visit;
	static TreeSet<Integer> waterH = new TreeSet<>();
	// static boolean[] height = new boolean[101];

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // N*N 배열
		map = new int[N][N];
		
		/* 입력받기
		 * 
		 * 건물 높이 -> 검사할 물 높이
		 * 중복제거 및 정렬위해 TreeSet에 저장
		 **/
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				map[i][j] = input;
				waterH.add(input);
			}
		}
		
		waterH.remove(waterH.last());	// 가장 높은 수위까지 물이 차면 안전영역 x, 가장 큰 값 제거
		
		// 물 높이별 안전영역 검사
		Iterator iter = waterH.iterator();
		while(iter.hasNext()) {
			visit = new boolean[N][N];
			
			int h = (int)iter.next();
			int num = find(h);	// 높이에 따른 안전영역 갯수 찾기
		
			safeArea = Math.max(safeArea, num);
		}

		System.out.println(safeArea);
		
	}

	// 높이에 따른 안전영역 갯수 찾기
	static int find(int h) {
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] || map[i][j] <= h)
					continue;

				bfs(j, i, h);
				result++;
			}
		}
		return result;
	}
	
	static void bfs(int x, int y, int h) {
		Queue<Point> q = new ArrayDeque<>();

		// 시작점 세팅
		// 전달받은 시작점(x,y)은 안전한 영역임
		visit[y][x] = true;
		q.add(new Point(x, y));
		
		// 4방향 탐색
		while (!q.isEmpty()) {
			Point p = q.poll();
			x = p.x;
			y = p.y;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx] || map[ny][nx] <= h)
					continue;

				q.add(new Point(nx, ny));
				visit[ny][nx] = true;

			}

		}

	}

}
