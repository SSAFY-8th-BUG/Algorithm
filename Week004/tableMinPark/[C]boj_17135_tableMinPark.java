import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_17135_tableMinPark {
	
	static int N, M, D, answer;
	static int[][] map, src;
	static class P{
		int y;
		int x;
		int d;
		public P(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public P(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
				
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		src = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				src[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		for (int i = 0; i < M - 2; i++) {
			for (int j = i + 1; j < M - 1; j++) {
				for (int k = j + 1; k < M; k++) {
					map = new int[N][M];
					for (int y = 0; y < N; y++) {
						for (int x = 0; x < M; x++) map[y][x] = src[y][x];
					}
					
					int count = 0;
					while(!check()) {
						count += kill(new int[] {i, j, k});
						move();
					}		
					answer = Math.max(answer, count);
				}
			}
		}
		
		System.out.println(answer);		
		br.close();
	}
	
	static int[] dy = {0, -1, 0};
	static int[] dx = {-1, 0, 1};
	static int kill(int[] sol) {
		int count = 0;

		List<P> killList = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			int x = sol[i];
			
			Queue<P> q = new ArrayDeque<>();
			boolean[][] v = new boolean[N][M];
			q.offer(new P(N, x, 0));
			
			while(!q.isEmpty()) {
				P now = q.poll();
								
				if (now.d > D) break;
				if (now.d > 0 && map[now.y][now.x] == 1) {
					killList.add(new P(now.y, now.x));
					break;
				}
				
				for (int j = 0; j < 3; j++) {
					int nextY = now.y + dy[j];
					int nextX = now.x + dx[j];
					
					if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
					if (v[nextY][nextX]) continue;
					
					q.add(new P(nextY, nextX, now.d + 1));
					v[nextY][nextX] = true;
				}
			}
		}
		
		for (P k : killList) {
			if (map[k.y][k.x] == 1) {
				map[k.y][k.x] = 0;
				count++;
			}
		}
		
		return count;
	}
	
	static void move() {	
		for (int y = N - 1; y > 0; y--) {
			for (int x = 0; x < M; x++) {
				map[y][x] = map[y - 1][x];
			}
		}
		for (int i = 0; i < M; i++) map[0][i] = 0;
	}
	
	static boolean check() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 1) return false;
			}
		}
		return true;
	}
}
