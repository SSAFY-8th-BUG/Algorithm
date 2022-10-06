package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_연구소_14502 {
	
	static int N, M, ans = Integer.MIN_VALUE;
	static int[][] map, backup;
	
	// 지도(map)에서 벽을 세울 수 있는 공간(0)을 저장한 list
	static List<Node> src = new ArrayList<>();
	// 바이러스의 위치를 미리 저장한 list
	static List<Node> virus = new ArrayList<>();
	
	// 조합(Combination)을 사용할 때 사용할 자료구조
	static Node[] tgt = new Node[3];
	
	// bfs를 위한 자료구조
	static Queue<Node> q = new ArrayDeque<>();
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		backup = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 벽을 세울 수 있는 자리 저장
				if (map[i][j] == 0) src.add(new Node(i, j));
				else if (map[i][j] == 2) virus.add(new Node(i, j));
			}
		}
		
		// backup 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				backup[i][j] = map[i][j];
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) {
			// complete code
			// 벽을 세운다.
			for (int i = 0; i < 3; i++) {
				Node n = tgt[i];
				map[n.x][n.y] = 1;
			}
			
			// bfs를 사용해 바이러스를 퍼뜨려본다.
			for (int i = 0; i < virus.size(); i++) {
				Node n = virus.get(i);
				q.add(n);
			}
			bfs();
			
			// 안전영역의 크기를 구한다.
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						sum++;
					}
				}
			}
			
			ans = Math.max(ans, sum);
			
			// map을 초기화해준다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = backup[i][j];
				}
			}
			
			return;
		}
		
		if (srcIdx == src.size()) return;
		
		tgt[tgtIdx] = src.get(srcIdx);
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
					map[nx][ny] = 2;
					q.add(new Node(nx, ny));
				}
			}
		}
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
