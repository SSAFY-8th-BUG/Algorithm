import java.io.*;
import java.util.*;

public class swea_2105_tableMinPark {
	
	static int T, N, answer;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N ;x++) map[y][x] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N ;x++) {
					for (int dr = 1; dr < N; dr++) {
						for (int dl = 1; dl < N; dl++) {
							search(y, x, dr, dl);
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer == 0 ? -1 : answer).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static boolean search(int y, int x, int dr, int dl) {
		List<Integer> stores = new ArrayList<>();; 
		
		int nowY = y;
		int nowX = x;
		
		for (int i = 0; i < dr; i++) {
			int nextY = nowY + 1;
			int nextX = nowX + 1;
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) return false;
			if (stores.contains(map[nextY][nextX])) return false;
			stores.add(map[nextY][nextX]);
			nowY = nextY;
			nowX = nextX;
		}
		
		// 좌하
		for (int i = 0; i < dl; i++) {
			int nextY = nowY + 1;
			int nextX = nowX - 1;
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) return false;
			if (stores.contains(map[nextY][nextX])) return false;
			stores.add(map[nextY][nextX]);
			nowY = nextY;
			nowX = nextX;
		}
		
		// 좌상
		for (int i = 0; i < dr; i++) {
			int nextY = nowY - 1;
			int nextX = nowX - 1;
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) return false;
			if (stores.contains(map[nextY][nextX])) return false;			
			stores.add(map[nextY][nextX]);
			nowY = nextY;
			nowX = nextX;
		}
		
		// 우상
		for (int i = 0; i < dl; i++) {
			int nextY = nowY - 1;
			int nextX = nowX + 1;
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) return false;
			if (stores.contains(map[nextY][nextX])) return false;
			stores.add(map[nextY][nextX]);
			nowY = nextY;
			nowX = nextX;
		}
		
		// 중복이 없고 범위를 초과하지 않는 경로만 저장되어 옴
		answer = Math.max(answer, stores.size());
		
		return true;
	}
}