package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_예산_2512 {
	
	static int N, M, left, right, ans;
	static int[] budget;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, budget[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		// 이분탐색으로 가능한 최댓값을 찾는다.
		while (left <= right) {
			int mid = (left + right) / 2;
			
			// mid 값으로 예산을 주었을 때, 예산이 남는 경우
			if (possible(mid)) {
				// ans와 비교해 더 큰 값을 넣는다.
				ans = Math.max(ans, mid);
				// left를 mid + 1로 고친다.
				left = mid + 1;
			} else {
				// mid 값으로 예산을 주었을 때, 예산이 모자란 경우
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean possible(int bg) {
		int sum = 0;
		for (int i = 0; i < budget.length; i++) {
			if (budget[i] < bg) sum += budget[i];
			else sum += bg;
		}
		
		if (sum <= M) return true;
		return false;
	}
}
