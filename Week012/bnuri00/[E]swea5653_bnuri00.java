package week012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Eswea5653_bnuri00 {
	static class Cell{
		int y, x, t, n;	// t: 남은시간, n: 처음생명력
		boolean active;
		public Cell(int y, int x, int t, int n) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
			this.n = n;
		}
		@Override
		public String toString() {
			return "Cell [y=" + y + ", x=" + x + ", t=" + t + ", n=" + n + ", active=" + active + "]";
		}
		
	}
	static int N, M, K;	// 세로, 가로, 배양시간
	static int[][] map = new int[651][651];
	static ArrayList<Cell> input = new ArrayList<>();
	static Queue<Cell> q = new ArrayDeque<>();
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
 	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			initMap();
			input.clear();
			q.clear();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int i = K-1; i < N+K-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = K-1; j < M+K-1; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						map[i][j] = n;
						input.add(new Cell(i, j, n, n));
					}
				}
			}
			Collections.sort(input, (c1, c2) ->c2.n-c1.n);
			q.addAll(input);
			logic();
			
			// 초기화 및 세포 세기
			System.out.println(q.size());
		}
	}

	private static void logic() {
		int time = 0;
		while(!q.isEmpty()) {
			if(time == K) break;
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Cell c = q.poll();
				
				if(!c.active && c.t != 0) {	// 세포 비활성
					c.t--;
					q.add(c);
					continue;
				} else if(c.active) {	// 이미 활성상태
					if(c.t != 0) {
						c.t--;
						q.add(c);
					}
					continue;
				}
				// else
				// 세포 활성화
				
				// 1보다 큰 경우만 큐에 넣음
				// 사방탐색
				if(c.n > 1) {
					c.active = true;
					c.t = c.n-2;
					q.add(c);
				}
				
				for (int d = 0; d < 4; d++) {
					int ny = c.y + dy[d];
					int nx = c.x + dx[d];
					

					if(map[ny][nx] !=0) continue;
					
					map[ny][nx] = c.n;
					q.add(new Cell(ny, nx, c.n, c.n));
				}
			}
			
			time++;
		}
		
	}

	private static void initMap() {
		//int tmp = K+K;
		for (int i = 0; i <651; i++) {
			for (int j = 0; j < 651; j++) {
				if(map[i][j] != 0) {

					map[i][j] = 0;
				}
			}
		}

	}

}
