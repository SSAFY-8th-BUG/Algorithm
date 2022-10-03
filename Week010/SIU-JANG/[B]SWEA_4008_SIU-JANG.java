package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_숫자만들기_4008 {
	
	static int T, N, max, min;
	static int[] numbers;
	static int[] oper;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			oper = new int[4];
			numbers = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			// dfs(사용한 연산자의 개수, 현재까지의 연산 결과)를 사용해 연산한다.
			dfs(1, numbers[0]);
			
			System.out.println("#" + test_case + " " + (max - min));
		}
	}
	
	static void dfs(int idx, int sum) {
		if (idx == N) {
			// complete code
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (i == 0 && oper[i] > 0) {
				oper[i]--;
				dfs(idx + 1, sum + numbers[idx]);
				oper[i]++;
			}
			
			if (i == 1 && oper[i] > 0) {
				oper[i]--;
				dfs(idx + 1, sum - numbers[idx]);
				oper[i]++;
			}
			
			if (i == 2 && oper[i] > 0) {
				oper[i]--;
				dfs(idx + 1, sum * numbers[idx]);
				oper[i]++;
			}
			
			if (i == 3 && oper[i] > 0) {
				oper[i]--;
				dfs(idx + 1, sum / numbers[idx]);
				oper[i]++;
			}
		}
	}
}
