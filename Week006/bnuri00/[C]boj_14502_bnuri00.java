package week006;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 3시간+ ??
 * 참고: boj 반례
 * 
 * 92144KB	364ms
 * 
 * <풀이방식>
 * - 완전탐색, bfs
 * 
 * - 이전에 세운 벽 이후로 탐색하며 다음벽을 세움
 * - 3개의 벽을 세우면 바이러스 퍼뜨리기, 바이러스 영역 크기++
 * - 바이러스 퍼뜨리기 완료 후 벽 영역, 바이러스 영역 등 이용해 안전영역 계산
 * 
 * <실수한거>
 * - 이전 벽 제외시키려고 for문 범위를 줄여버려 벽이 안세워지는 경우가 너무 많아짐
 * 		(x, y부터 시작하여 x 이전의 열을 모두 검사제외함)
 * */
// boj 14502 연구소
public class Cboj_14502_bnuri00 {
	static int N, M, total;	// 세로, 가로, 전체 맵 크기
	
	static int[][] map;		// 
	static int[][] origin;	// 원본 맵 상태 저장
	
	static Point p; 	// 벽 세운 위치 기록
	static ArrayList<Point> virusStart = new ArrayList<>(); 	// 입력에서 바이러스 위치
	static Point[] wall;
	
	// 초반 벽 영역, 초반 바이러스 영역, 퍼져나간 바이러스 영역, 안전영역
	static int wallArea, virusAreaStart, virusArea, safeArea;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		total = N*M;
		map = new int[N][M];
		origin = new int[N][M];
		
		// 벽 저장할 배열, 객체 만들어주기
		wall = new Point[3];
		for (int i = 0; i < 3; i++) {
			wall[i] = new Point(0, 0);
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				origin[i][j] = n;
				
				if(n == 2) {
					virusStart.add(new Point(j, i));	// 처음 바이러스 위치 저장
					virusAreaStart++;	// 처음 바이러스 개수
				} else if (n == 1) {
					wallArea++;	// 벽 개수
				}

			}
		}
		
		p = new Point();
		
		// y, x, cnt
		//  0, -1 로 지정? 이미 검사한 부분 제외하는 코드에 j<=x이 있어 0,0을 넣으면 해당 위치를 검사할 수 없음
		setWall(0, -1, 0);	

		System.out.println(safeArea);

	}
	// bfs로 바이러스 퍼뜨리기
	// 각 바이러스 초기위치에서 시작
	static void virusSpread() {
		for (int i = 0; i < virusStart.size(); i++) {
			Point p = virusStart.get(i);
			bfs(p.y, p.x);
		}
	}
	static void bfs(int y, int x) {
		Queue<Point> q = new ArrayDeque<>();
		
		
		q.add(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >=M || map[ny][nx] != 0) continue;
				
				q.add(new Point(nx, ny));
				map[ny][nx] = 2;
				virusArea++;
				
			}
		}
	}
	// 맵을 원상복구
	static void resetMap() {
		for (int i = 0; i < N; i++) {
			map[i] = origin[i].clone();
		}
	}
	// 벽 세우고 3개 다 세우면 안전영역 계산
	static void setWall(int y, int x, int cnt) {
		if(cnt == 3) {	// 벽 3개 다 세워지면
			
			virusArea = virusAreaStart;	// 바이러스 영역을 초기값으로
			for(int i = 0; i < 3; i++) {
				map[wall[i].y][wall[i].x] = 1;
			}	
			
			virusSpread();	// 바이러스 퍼진다
						
			int curr = total-virusArea-wallArea-3;
			if(safeArea < curr) {	// 기존 안전영역이 현재 안전영역보다 작다면 교체
				safeArea = curr;
			}
			
			resetMap();	// 다음 탐색 위한 맵 초기화
			return;
		}
		
		for (int i = y; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(i==y &&j<=x) continue;	// 이미 검사한 부분 제외
				if(map[i][j] == 0) {
					wall[cnt].setLocation(j, i);	// cnt번째 벽 위치 기록
					
					setWall(i, j, cnt+1);
				}
			}
		}
	}

}
