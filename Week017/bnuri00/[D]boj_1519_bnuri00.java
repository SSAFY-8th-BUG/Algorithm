package week017;

import java.util.*;

public class D_boj_1519_bnuri00 {
	static int N;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		if(N < 20) {
			if(N < 10) System.out.println(-1);
			else System.out.println(N%2==0? 1: -1);
		}else {
			// 초기 dp 설정
			dp = new int[N+1];
			for (int i = 10; i < 20; i++) {
				if(i%2==0) dp[i] = 1;
			}
			System.out.println(logic());
		}
		
	}
	static int logic() {
		for (int n = 20; n <= N; n++) {
			String str = String.valueOf(n);
			Set<String> set = new HashSet<>(); // 진 부분 문자열 집합.
			for (int start = 0; start < str.length(); start++) {
				// 0으로 시작하면 안 됨.
				if (str.charAt(start) == '0') {
					continue;
				}

				String res = "";
				for (int i = start; i < str.length(); i++) {
					res += str.charAt(i);

					if (!res.equals(str)) {
						set.add(res);
					}
				}
			}
			
			Iterator<String> it = set.iterator();

			int min = Integer.MAX_VALUE;
			while (it.hasNext()) {
				int num = Integer.parseInt(str);
				int tmp = Integer.parseInt(it.next());

				// 다음 게임의 상황이 패배하면 현재 게임은 반드시 승리
				if (dp[num - tmp] == 0) {
					min = Math.min(min, tmp);
				}
			}

			if (min != Integer.MAX_VALUE) {
				dp[n] = min;
			}

		}
		return dp[N]==0? -1:dp[N];
	}

}
