package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA5653_2 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static final int MAX = 300 * 2 + 50 + 1;
	static final int DEAD = 0, ACTIVE = 1, INACTIVE = 2;

	static int T, N, M, K, ans;

	static boolean[][] map;
	static List<Cell> cell;
	static PriorityQueue<Cell> pq=new PriorityQueue<>( (Cell c1, Cell c2) -> c2.p-c1.p);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new boolean[MAX][MAX];
			cell = new ArrayList<>();
			pq=new PriorityQueue<>( (Cell c1, Cell c2) -> c2.p-c1.p);
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int p = Integer.parseInt(st.nextToken());
					if (p == 0)
						continue;

					cell.add(new Cell(i + 300, j + 300, p, p));
					map[i + 300][j + 300] = true;
				}
			}
			ans = 0;

			simulation();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void simulation() {
		for (int k = 1; k <= K; k++) {

			while (!pq.isEmpty()) {
				Cell c=pq.poll();
				if (map[c.y][c.x]) continue;
				map[c.y][c.x]=true;
				
				cell.add(c);
			}
			
			for (Cell c: cell) {
				if (c.state==DEAD) continue;
				
				else if (c.state==INACTIVE && c.turn==k) {
					c.state=ACTIVE;
					c.turn=k+c.p;
					
					for (int d=0; d<4; d++) {
						int ny=c.y+dy[d];
						int nx=c.x+dx[d];
						
						pq.add(new Cell (ny, nx, c.p, k+1+c.p));
					}
				} else if (c.state==ACTIVE && c.turn==k) {
					c.state=DEAD;
					c.turn=0;
					c.p=0;
				}		
			}
		}

		for (Cell c : cell) {
			if (c.state==ACTIVE || c.state==INACTIVE)
				ans++;
		}
	}


	static class Cell {
		int y, x, p, state, turn;

		Cell(int y, int x, int p, int turn) {
			this.y = y;
			this.x = x;
			this.p = p;
			this.turn = turn;
			this.state = INACTIVE;
		}
	}
}
