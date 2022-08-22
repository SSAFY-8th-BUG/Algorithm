package boj;

import java.util.Scanner;

public class BOJ_로마숫자만들기_16922 {
	
	static int N, ans;
	static boolean[] checked;
	static int[] d = { 1, 5, 10, 50 };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		checked = new boolean[N * 50 + 1];
		ans = 0;
		dfs(0, 0, 0);		
		System.out.println(ans);
	}
	
	static void dfs(int sum, int cnt, int idx) {
		if (cnt == N) {
			if (!checked[sum]) {
				ans++;
				checked[sum] = true;
			}
			
			return;
		}
		
		for (int i = idx; i < 4; i++) {
			dfs(sum + d[i], cnt + 1, i);
		}
	}
}
