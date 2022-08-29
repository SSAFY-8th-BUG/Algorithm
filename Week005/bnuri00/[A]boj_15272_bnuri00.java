import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 
 * 풀이시간: 3시간?
 * 참고: 순열 코드...
 * 
 * 19592KB	584ms
 * 
 * <풀이방식>
 * 
 * <실수한거>
 * - 주자를 옮기는 run 메소드에서 옮긴 후 이전 자리를 처리 안해서 주자 복제됨
 * */
public class Aboj_15272_야구 {
	static int N, currScore, maxScore;
	static int[][] scoreMap;
	
	static boolean[] base = new boolean[3];
	
	
	static boolean[] select;
	static int[] order;
	public static void main(String[] args) throws Exception{
		order = new int[8];
		select = new boolean[9];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		scoreMap = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				scoreMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		perm(0);
		System.out.println(maxScore);

	}
	
	static int homeRun() {
		int score = 0;
		for(int i = 0; i < 3; i++) {
			if(base[i]) {
				score++;
				base[i] = false;
			}
		}
		return score+1;
	}
	
	static int run(int hit) {
		int score = 0;
		for(int i = 2; i >= 0; i--) {
			if(i < 3-hit) {//
				base[i+hit] = base[i];
				base[i] = false;
			} else {
				if(!base[i]) continue;
				
				score++;
				base[i] = false;
			}
		}
		base[hit-1] = true;
		
		return score;
	}
	
	static void game() {
		int outCnt = 0;
		int currIdx = 0;
		
		for(int turn = 0; turn < N; ) {
	
			int hit = -1;
			
			
			if(currIdx==3) {	// 4번타자 (0번선수)
				hit = scoreMap[turn][0];
			} else {
				if(currIdx == 9) currIdx = 0;
				
				if(currIdx>3) hit = scoreMap[turn][order[currIdx-1]];
				else hit = scoreMap[turn][order[currIdx]];
				
			}
			
			currIdx++;
			
			switch(hit) {
			case 0:
				outCnt++;
				break;
			case 4:
				currScore += homeRun();
				break;
			default:
				currScore += run(hit);
				break;
			}
			
			
			if(outCnt == 3) {
				turn++;
				outCnt = 0;
				
				// 베이스에 주자 없애기
				Arrays.fill(base, false);
				
			}
		}
	}

	static void perm(int tgtIdx) {
		if(tgtIdx==8) {
			
			
			game();
			
			maxScore = Math.max(maxScore, currScore);
//			
//			if(currScore==maxScore) {
//				System.out.println("************************");
//				System.out.println(Arrays.toString(order));
//				System.out.println(currScore);
//			}
			currScore = 0;
			return;
		}
		
		for(int i = 1; i <= 8; i++) {
			if(select[i]) continue;
			
			select[i] = true;
			order[tgtIdx] = i;
			perm(tgtIdx+1);
			select[i] = false;

			
		}
	}
}
