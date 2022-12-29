package Week017;

import java.io.*;

// 한자리수면 짐
// 메모이제이션
public class bj_1519_tableMinPark {
	
	static int N;
	static int[] memoi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memoi = new int[1_000_001];
		
		System.out.println(solve(N));		
		br.close();
	}
	
	static int solve(int now) {
		// 이미 계산한 값이 있으면 리턴
		if (memoi[now] != 0) return memoi[now];
		// 한자리수이면 진 것이기 때문에 -1 리턴
		if (now < 10) return memoi[now] = -1;
		
		int next = Integer.MAX_VALUE;
		boolean v = false;
		
		// 그게 아니면 계산 
		// 계산하는 중에 결과값 중 패배 (-1) 나와야함 안나오면 진 것
		String strNow = String.valueOf(now);
		for (int len = 1; len < strNow.length(); len++) {
			for (int start = 0; start <= strNow.length() - len; start++) {
				int sub = Integer.parseInt(strNow.substring(start, start + len));
				if (sub == 0) continue;		
				// 한번 더 내려갔을 때 -1 이면 플레이어가 이긴 것이기 떄문에 v = true
				if (solve(now - sub) == -1) {
					v = true;
					next = Math.min(next, sub);
				}
			}
		}
		// 진 경우
		if (!v) return memoi[now] = -1;
		// 이긴 경우
		return memoi[now] = next;
	}
}
