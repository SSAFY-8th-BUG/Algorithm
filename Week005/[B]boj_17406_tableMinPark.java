import java.io.*;
import java.util.*;

// 배열돌리기
public class boj_17406_tableMinPark {
	
	static int N, M, K, answer;
	static int[][] A;
	static P[] rot;
	static int[] tgt;
	static boolean[] v;
	
	static class P{
		int r;
		int c;
		int s;
		public P(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) A[y][x] = Integer.parseInt(st.nextToken());
		}
		
		rot = new P[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			rot[i] = new P(r, c, s);
		}
		
		answer = Integer.MAX_VALUE;
		tgt = new int[K];
		v = new boolean[K];
		perm(0);
		
		System.out.println(answer);
		br.close();
	}
	
	static void perm(int idx) {
		if (idx == K) {
			int[][] map = new int[N][M];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) map[y][x] = A[y][x];
			}
			
			for (int i = 0; i < K; i++) rotate(rot[tgt[i]].r, rot[tgt[i]].c, rot[tgt[i]].s, map);

			for (int y = 0; y < N; y++) {
				int sum = 0;
				for (int x = 0; x < M; x++) sum += map[y][x];
				answer = Math.min(answer, sum);	
			}		
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (v[i]) continue;
			tgt[idx] = i;
			v[i] = true;
			perm(idx + 1);
			v[i] = false;
		}
	}
	
	static void rotate(int r, int c, int s, int[][] map) {
		int sy = r - s;
		int sx = c - s;
		int ey = r + s;
		int ex = c + s;
		
		
		while(sy < ey && sx < ex) {
			int temp = map[sy][sx];			
			// 왼
			for (int y = sy; y < ey; y++) map[y][sx] = map[y + 1][sx];			
			// 아래
			for (int x = sx; x < ex; x++) map[ey][x] = map[ey][x + 1];
			// 오
			for (int y = ey; y > sy; y--) map[y][ex] = map[y - 1][ex];			
			// 위
			for (int x = ex; x > sx; x--) map[sy][x] = map[sy][x - 1];			
			map[sy][sx + 1] = temp;	
			
			sy++;
			sx++;
			ey--;
			ex--;
		}
	}
}
