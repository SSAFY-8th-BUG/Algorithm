package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_치즈_2636 {
	
	static int N, M, ans, count;
	static int[][] map;
	static Set<Node> willBeRemoved = new HashSet<>();
	
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
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			// 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1) {
						map[i][j] = 0;
					}
				}
			}
			// 제일 바깥쪽 요소를 돌면서 dfs를 사용해 -1로 채워준다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if ((i == 0 || i == N - 1 || j == 0 || j == M - 1) && map[i][j] == 0) {
						dfs(i, j);
					}
				}
			}
			
			// -1과 접하거나 벽과 접하는 치즈(1)의 영역을 HashSet에 넣고 한번에 없애준다.
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					for (int d = 0; d < 4; d++) {
						int ni = i + dx[d];
						int nj = j + dy[d];
						
						// 우선 map[i][j] == 1이어야 한다.
						if (map[i][j] == 1) {
							// i 와 j의 범위를 1부터 N - 1, M - 1까지로 했으므로 범위 체크는 필요없다.
							if(map[ni][nj] == -1) {
								willBeRemoved.add(new Node(i, j));
							}
						}
					}
				}
			}
			
			// HashSet에 들어있는 원소의 개수를 count에 저장한다.
			count = willBeRemoved.size();
			
			// HashSet에 저장되어 있는 좌표를 -1로 바꿔주면서 ans를 1 증가시킨다.
			Iterator<Node> itr = willBeRemoved.iterator();
			while (itr.hasNext()) {
				Node n = itr.next();
				map[n.x][n.y] = -1; 
			}
			
			ans++;
			willBeRemoved.clear();
			
			// 전부 -1이면 break 해준다.
			boolean finish = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						finish = false;
						break;
					}
				}
				if (!finish) break;
			}
			
			if (finish) break;
		}
		
		System.out.println(ans);
		System.out.println(count);
	}
	
	static void dfs(int x, int y) {
		map[x][y] = -1;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
				dfs(nx, ny);
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
		
		// hashSet에서 중복을 피하기 위한 equals와 hashCode 재정의
		@Override
		public boolean equals(Object o) {
			if (o instanceof Node) {
				Node n = (Node) o;
				if (this.x == n.x && this.y == n.y) {
					return true;
				}
				return false;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
