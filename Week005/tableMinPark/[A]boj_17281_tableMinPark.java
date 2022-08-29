import java.io.*;
import java.util.*;

// (야구공)
public class boj_17281_tableMinPark {
	
	static int N, answer;
	static int[][] score;
	static int[] tgt;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		score = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) score[i][j] = Integer.parseInt(st.nextToken());
		}
		
		tgt = new int[9];
		v = new boolean[9];
		answer = 0;
		
		perm(0);
		
		System.out.println(answer);
		br.close();
	}
	
	static void perm(int tgtIdx) {
		if (tgtIdx == 9) {
			// 순열 생성 -> 게임진행
			answer = Math.max(answer, game());
			return;
		}
		
		if (tgtIdx == 3) perm(tgtIdx + 1);
		else {			
			for (int i = 1; i < 9; i++) {
				if (v[i]) continue;
				v[i] = true;
				tgt[tgtIdx] = i;
				perm(tgtIdx + 1);
				v[i] = false;
			}
		}
	}
	
	static int game() {				
		int sum = 0;
		int next = 0;
		boolean[] base;
		
		// 이닝만큼 반복
		for (int ening = 0; ening < N; ening++) {
			int out = 0;		// 한 이닝에서 나오는 아웃 수
			base = new boolean[4];
						
			while(true) {
				if (out == 3) break;
				
				int ta = score[ening][tgt[next++ % 9]];
				
				switch(ta) {
					// 아웃
					case 0:
						out++;
						break;
					// 1루타
					case 1:
						if (base[3]) sum++;
						for (int b = 3; b > 0; b--) base[b] = base[b - 1];
						base[1] = true;			// 타자진루
						break;						
					// 2루타	
					case 2:
						if (base[3]) sum++;
						if (base[2]) sum++;
						base[3] = base[1];
						base[2] = true;			// 타자진루
						base[1] = false;
						break;
					// 3루타
					case 3:
						if (base[3]) sum++;
						if (base[2]) sum++;
						if (base[1]) sum++;
						base[3] = true;			// 타자진루
						base[2] = false;
						base[1] = false;							
						break;
					// 홈런	
					case 4:
						sum++;
						if (base[3]) sum++;
						if (base[2]) sum++;
						if (base[1]) sum++;
						base[3] = base[2] = base[1] = false;
						break;
				}
			}
		}
		return sum;
	}	
}
