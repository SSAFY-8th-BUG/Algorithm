import java.io.*;
import java.util.*;

public class swea_5644_tableMinPark {
	
	static int T, M, A, answer;
	static int[] aMove, bMove;
	static AP[] aps;
	static int ay, ax, by, bx;
	static int[] dy = {0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 1, 0, -1};
	
	static class AP{
		int y;
		int x;
		int c;
		int p;
		public AP(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			aMove = new int[M];
			bMove = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) aMove[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) bMove[i] = Integer.parseInt(st.nextToken());
			
			aps = new AP[A + 1];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				aps[i] = new AP(y, x, c, p);
			}
			
			answer = 0;
			ay = ax = 1;
			by = bx = 10;
			
			charge();
			
			for (int i = 0; i < M; i++) {
				ay += dy[aMove[i]];
				ax += dx[aMove[i]];
				by += dy[bMove[i]];
				bx += dx[bMove[i]];
				
				charge();
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static int getPower(int y, int x, AP ap) {
		if (Math.abs(ap.y - y) + Math.abs(ap.x - x) <= ap.c) return ap.p;
		return 0;		
	}
	
	// 모든 조합 비교 (중복조합)
	static void charge() {
		
		int max = 0;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int aTemp = getPower(ay, ax, aps[i]);
				int bTemp = getPower(by, bx, aps[j]);
				
				if (aTemp == 0 && bTemp == 0) continue;
				
				// 두 개가 하나의 AP를 쓸 때
				if (i == j) max = Math.max(max, Math.max(aTemp, bTemp));
				else max = Math.max(max, aTemp + bTemp);
			}
		}
		answer += max;
		
	}
}