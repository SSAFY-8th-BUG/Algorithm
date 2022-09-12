package week007;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 2시간+
 * 참고: 연구소 코드ㅎㅎㅎㅎ
 * 
 * 28920KB	260ms
 * 
 * <풀이방식>
 * - 완전탐색, bfs
 * 
 * 
 * */
// boj 17141 연구소 2
public class Cboj_17141_연구소2 {
	static int N, M;	// 맵 크기 (N*N), 바이러스 개수	
	
	static int[][] map;
	static boolean[][] visit;

	static ArrayList<Point> virusCanPut = new ArrayList<>(); 	// 입력에서 바이러스 위치
	static Point[] virus;
	
	// 초반 벽 영역, 퍼져나간 바이러스 영역, 바이러스 퍼지는 최소시간(답)
	static int wallArea, virusArea, minTime = Integer.MAX_VALUE;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		// 바이러스 저장할 배열, 객체 만들어주기
		virus = new Point[M];
		for (int i = 0; i < M; i++) {
			virus[i] = new Point(0, 0);
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				
				if (n == 1) {
					wallArea++;	// 벽 개수
				}else if(n==2) {
					virusCanPut.add(new Point(j, i));
				}

			}
		}
		
		
		
		setVirus(0, 0);	

		if(minTime == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minTime);

	}

	static int virusSpread() {
		Queue<Point> q = new ArrayDeque<>();
		
		
		for(Point v : virus) {
			q.add(v);
			visit[v.y][v.x] = true;
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			if(N*N -virusArea-wallArea == 0) return time;
			
			time++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					
					//if(N*N -virusArea-wallArea == 0) return time;
					
					if(ny < 0 || nx < 0 || ny >= N || nx >=N || map[ny][nx] == 1 || visit[ny][nx]) continue;
					
					q.add(new Point(nx, ny));
					visit[ny][nx] = true;
					virusArea++;
					
				}
			}
			
			
		}
		return time;
	}
	static void cleanVisit() {
		for (int i = 0; i < visit.length; i++) {
			Arrays.fill(visit[i], false);
		}
	}
	// 벽 세우고 3개 다 세우면 안전영역 계산
	static void setVirus(int src, int cnt) {
		if(cnt == M) {	// 바이러스 M개 놓아지면
//			System.out.println("*************************");
//			for (int i = 0; i < virus.length; i++) {
//				System.out.println(virus[i].x + " " + virus[i].y);
//			}
//			return;
			
			cleanVisit();
			virusArea = M;	// 바이러스 영역수를 초기값으로
			
			int time = virusSpread();	// 바이러스 퍼진다
						
			if(N*N -virusArea-wallArea == 0) {	
				minTime = Math.min(minTime, time);
			}

			return;
		}
		
		for (int i = src; i < virusCanPut.size(); i++) {
			Point p = virusCanPut.get(i);
			virus[cnt].setLocation(p.x, p.y);
			setVirus(i+1, cnt+1);
		}
		
	}

}
