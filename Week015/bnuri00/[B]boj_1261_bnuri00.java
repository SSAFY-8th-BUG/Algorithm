package week015;

import java.awt.Point;
import java.io.*;
import java.util.*;

/*
 * 잘못한거 : 큐에 다 넣어버려서 메모리초과됨 (다익스트라 배열의 값보다 작을때만 넣어야함) */
public class B_boj_1261_bnuri00 {
	static class Node{
		int y, x, cost;
		public Node() {}
		public Node(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}
	static int N, M;	// 세로, 가로
	static int min;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static char[][] map;
	static int[][] dijk;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		//min = N+M-3;
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dijk = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dijk[i][j] = 987654321;
			}
		}
		setDijk();
		System.out.println(dijk[N-1][M-1]);
	}
	private static void setDijk() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cost-o2.cost);
		pq.add(new Node());
		dijk[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(dijk[n.y][n.x] < n.cost) continue;
			
			if(n.y==N-1 && n.x==M-1){
				dijk[n.y][n.x] = n.cost;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = n.y + dy[d];
				int nx = n.x + dx[d];				
				
				if(ny < 0 || nx < 0|| ny >= N || nx >= M ) continue;
				int cost = n.cost + (map[ny][nx]=='1'? 1 : 0);
				if(cost < dijk[ny][nx]) {
					dijk[ny][nx] = cost;
					pq.add(new Node(ny, nx, cost));
				}
			}
		}
	}

}