package week010;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이시간: 1시간 20분쯤?
 * 참고: X
 * 
 * 
 * 
 * */
public class Aswea_1953_bnuri00 {
	// 지도의 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 탈출 후 소요된 시간 L
	static int N, M, R, C, L;
	
	static int[][] map;
	
	// 터널 번호에 따른 갈 수 있는 위치
	final static int[][] tunnel = {
			{},	// 0번은 못감
			{0, 1, 2, 3},	// 상하좌우
			{0, 1},	// 상 하
			{2, 3},	// 좌 우
			{0, 3},	// 상 우
			{1, 3},	// 하 우
			{1, 2},	// 하 좌
			{0, 2}	// 상 좌
	};
	
	// 상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			// 지도 입력받기
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = bfs();
			System.out.println("#"+t+" "+result);
			
		}
		
	}

	private static int bfs() {	
		
		boolean[][] visit = new boolean[N][M];
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(C, R));
		visit[R][C] = true;
		
		int time = 1;	// 움직인 시간 
		int loc = 1;	// 새로운 위치 수 카운팅
		
		while(!q.isEmpty()) {
			
			if(time == L) return loc;	
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				int type = map[p.y][p.x];
				
				
				for (int j = 0; j < tunnel[type].length; j++) {
					int dir = tunnel[type][j];
					
					int ny = p.y + dy[dir];
					int nx = p.x + dx[dir];
					
					if(ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]|| map[ny][nx] == 0) continue;
					if(!canGo(dir, map[ny][nx])) continue;

					q.add(new Point(nx,ny));
					loc++;	// 새로운 위치
					visit[ny][nx] = true;
				}
				
			}
			
			time++;		
		}
			
		return loc;
	}

	// 진입방향, 다음 위치 터널 타입으로 갈 수 있는지 확인
	static boolean canGo(int dir, int type) {
		if(type == 1) return true;
		
		// 진입방향 구하기
		// 이전 위치에서의 dir이 상 이면 
		// 아래에서 다음 위치로 가는 것,, dir 반대로 바꿔주기
		if(dir == 0) dir = 1;
		else if(dir == 1) dir = 0;
		else if(dir == 2) dir = 3;
		else if(dir == 3) dir = 2;
		
		for (int i = 0; i < tunnel[type].length; i++) {
			if(tunnel[type][i] == dir) return true;
		}
			
		return false;
	}

}
