package week004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 풀이시간: 5+ 시간 (자잘한 실수 많음..)
 * 참고: 조합 코드 (으악으악)
 * 
 * 33372 KB		232 ms
 * 
 * <풀이방식>
 * - 궁수 위치 조합
 * - 조합별로 궁수 위치 bfs, 궁수가 쏜 적 위치 set에 저장(중복제거)
 * - 적 카운팅, set에 있는 적 삭제 후 map 한칸 내림
 * - N(map 행) 만큼 반복
 * 
 * 
 * <삽질목록>
 * - 다른 맵으로 상태 바꿔주는 turn 메소드에서 배열 인덱스 계속 헷갈림
 * - bfs에서 방문한 갯수로 depth 파악하려고 함(이렇게 하려면 예외가 너무 많아서 불가, 대신 거리 계산해 종료조건으로 함)
 * - 2차원 배열은 바로 clone하면 깊은복사x, 각각 1차원배열을 clone해주어야함
 * - 기타 초기화 실수, 빼먹고 안한거...등등
 * 
 * */

public class C_boj_캐슬디펜스 {
	static int N, M, D, result;	// 행, 열, 궁수 공격거리, 결과
	static int[][] map;
	static int[][] testMap;
	
	static int[] emptyLine;
	
	// 좌 상 우
	static int[] dy = {0, -1, 0};
	static int[] dx = {-1, 0, 1};
	
	static int[] dst = new int[3];	// 궁수 위치 저장 (조합)
	
	static Set<Point> set = new HashSet<>();	// 적 위치 저장
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		
		map = new int[N][M];
		testMap = new int[N][];
		emptyLine = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(result);
	}
	
	static void comb(int src, int dstIdx) {
		if(dstIdx==3) {
			// complete code
			defence();
			return;
		}
		if(src==M) return;
		

		dst[dstIdx] = src;
		comb(src+1, dstIdx+1);
		comb(src+1, dstIdx);	// 현재 선택x
		
	}
	
	static void defence() {
		// 초기화
		set.clear();
		for(int i = 0; i < N; i++)
			testMap[i] = map[i].clone();
		
		
		int cnt = 0;
		
		Point p1 = new Point(dst[0],N-1);
		Point p2 = new Point(dst[1],N-1);
		Point p3 = new Point(dst[2],N-1);

		for(int i = 1; i <= N; i++) {
			bfs(p1);
			bfs(p2);
			bfs(p3);
			
			cnt += set.size();
			
			if(i==N) continue;
			
			turn(i);
			set.clear();
		}
		result = Math.max(cnt, result);
	}
	
	// 다음턴으로 맵 상태 바꿔줌
	static void turn(int t) {
		
		Iterator<Point> iter= set.iterator();
		while(iter.hasNext()) {
			Point p = iter.next();
			testMap[p.y][p.x] = 0;
			//System.out.println(p);
		}

		
		for(int i = N-1; i >=t; i--) {
			testMap[i] = testMap[i-1];
		}
		testMap[t-1] = emptyLine;
	}
	
	static void bfs(Point startP) {
		if(testMap[startP.y][startP.x] == 1) {
			set.add(startP);
			return;
		}
		
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];
		
		q.add(startP);
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i = 0; i < 3; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(nx<0||ny<0||nx>=M||ny>=N||visit[ny][nx]) continue;
				
				if(Math.abs(startP.x-nx)+Math.abs(startP.y-ny) > D-1) return;

				
				Point tmp = new Point(nx,ny);
				if(testMap[ny][nx] == 1) {	// 적이 있는 공간이면 종료하기
					set.add(tmp);
					return;
				}
				q.add(tmp);
				visit[ny][nx] = true;		
			}
			
		}
		
		
	}

}
